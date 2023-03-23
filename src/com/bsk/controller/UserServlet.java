package com.bsk.controller;


import com.bsk.po.User;
import com.bsk.service.UserService;
import com.bsk.service.impl.UserServiceImpl;
import com.bsk.util.CodeImgUtil;
import com.bsk.util.MD5Utils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@WebServlet("/UserServlet")
public class UserServlet extends BaseServlet {
    private UserService userService = new UserServiceImpl();

    public void login(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获取所有的参数键值对
        Map<String, String[]> parameterMap = request.getParameterMap();
        //构建封装对象
        User u = new User();
        //参数封装到对象中
        BeanUtils.populate(u, parameterMap);



        u.setUserPwd(MD5Utils.encrypt(u.getUserPwd()));

        //调用服务层执行登录
        User user = userService.login(u);

        //判断
        if (user != null) {
            if (user.getUserStatus().equals("Y")){
            //保存登录用户对象
            request.getSession().setAttribute("user", user);
            //给信号
            response.getWriter().print("Y");
            }else{
                //给信号
                    response.getWriter().print("A");

            }
        } else {



                response.getWriter().print("N");


        }

    }

    public void register(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获取所有的参数键值对
        Map<String, String[]> parameterMap = request.getParameterMap();
        //构建封装对象
        User u = new User();
        try {
            //参数封装到对象中
            BeanUtils.populate(u, parameterMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String encrypt = MD5Utils.encrypt((u.getUserPwd()));
        u.setUserPwd(encrypt);
        int register = userService.register(u);
        if (register != 0) {
            User user = userService.login(u);
            if (user != null) {
                //保存登录用户对象
                request.getSession().setAttribute("user", user);
                //给信号
                response.getWriter().print("Y");
            } else {
                //给信号
                response.getWriter().print("N");
            }
        }


    }

//    电话号码查重
public void log(HttpServletRequest request, HttpServletResponse response) throws Exception {
    String userTel = request.getParameter("userTel");
    User user = new User();
    user.setUserTel(userTel);
    User log = userService.log(user);
    if (log !=null){
        response.getWriter().print("Y");
    }
}

    public void out(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.getSession().removeAttribute("user");
        response.sendRedirect("main.jsp");
    }

    public void update(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获取所有的参数键值对
        Map<String, String[]> parameterMap = request.getParameterMap();
        //构建封装对象
        User u = new User();
        //参数封装到对象中
        BeanUtils.populate(u, parameterMap);



         int update = userService.update(u);
        User user = userService.login(u);
        if(update!=0){
//            替换用户
            request.getSession().setAttribute("user", user);
            response.getWriter().print("Y");
        }else{
            response.getWriter().print("N");
        }


    }
    public void codeImg(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String code = CodeImgUtil.drawImage(output);
        System.out.println(code);
        // 将验证码文本直接存放到session中
        request.getSession().setAttribute("code", code);
        try {
            ServletOutputStream out = response.getOutputStream();
            output.writeTo(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changePwd(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String oldPwd = MD5Utils.encrypt(request.getParameter("oldPwd"));
        String newPwd = MD5Utils.encrypt(request.getParameter("newPwd"));
        String code = request.getParameter("code");

        //获取当前登录用户对象
        User user = (User) request.getSession().getAttribute("user");



        //验证验证码
        String code1 = (String) request.getSession().getAttribute("code");
        if(code1.equalsIgnoreCase(code)){
            //密码加密待实现

            //验证旧密码
            if(user.getUserPwd().equals(oldPwd)){
                if (newPwd.equals(oldPwd)) {
                    response.getWriter().print("F");
                } else {
                    //执行密码修改（调用service实现修改密码）
                    user.setUserPwd(newPwd);
                    int update = userService.update(user);

                    //清理session
                    request.getSession().removeAttribute("user");
                    //输出信号
                    response.getWriter().print('Y');
                }
            }else{
                response.getWriter().print(1);
            }
        }else{
            response.getWriter().print(0);
        }
    }
    public void userList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            User user = (User)request.getSession().getAttribute("user");
            if(user.getUserRole().equals("A")){
                String startTime = "".equals(request.getParameter("startTime"))?null:request.getParameter("startTime");
                String endTime = "".equals(request.getParameter("endTime"))?null:request.getParameter("endTime");
                String userTel = request.getParameter("userTel");

                user = new User();
                user.setUserRole("U");
                if(userTel!=null && !"".equals(userTel)){
                    user.setUserTel(userTel);
                }

                if(startTime != null && endTime == null){
                    LocalDateTime date = LocalDateTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    endTime = formatter.format(date);
//                    endTime = "2023-03-14 0:0:0";
                }

                if(startTime == null && endTime != null){
                    startTime = "2000-01-01 0:0:0";
                }


                //当前页
                int pageNum;
                if(request.getParameter("pageNum")!=null){
                    pageNum = Integer.parseInt(request.getParameter("pageNum"));
                }else{
                    pageNum = 1;
                }
                //每页数据大小
                int pageSize = 5;
                //总数据量
                int total = userService.search(user,0,0,startTime,endTime).size();
                //总页数
                int pages = total % pageSize == 0 ? total / pageSize : total / pageSize + 1 ;
                //页数矫正
                if(pageNum > pages){
                    pageNum = pages;
                }
                if(pageNum < 1){
                    pageNum = 1;
                }



                List<User> userList = userService.search(user,pageNum,pageSize,startTime,endTime);
                request.setAttribute("startTime",startTime);
                request.setAttribute("endTime",endTime);
                request.setAttribute("userTel",userTel);
                request.setAttribute("pageNum",pageNum);
                request.setAttribute("pages",pages);
                request.setAttribute("userList",userList);
                request.getRequestDispatcher("user_list.jsp").forward(request,response);
            }else{
                List<User> userList = null;
                request.getRequestDispatcher("user_list.jsp").forward(request,response);
            }

        }
//账户停用启用
    public void updateStatus(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userId = request.getParameter("userId");
        String userStatus = request.getParameter("userStatus");
        User user = new User();
        user.setUserId(Integer.parseInt(userId));
        user.setUserStatus(userStatus);
        int update = userService.update(user);
        if (update==1){
            response.getWriter().print("Y");
        }
    }
//    重置密码
            public void againPwd(HttpServletRequest request, HttpServletResponse response) throws IOException {
                String userId = request.getParameter("userId");
                User user = new User();
                user.setUserId(Integer.parseInt(userId));
                user.setUserPwd("123456");
                user.setUserPwd(MD5Utils.encrypt(user.getUserPwd()));
                int update = userService.update(user);
                if (update==1){
                    response.getWriter().print("Y");
                }
            }

}




