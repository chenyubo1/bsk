package com.bsk.controller;

import com.bsk.po.Address;
import com.bsk.po.User;
import com.bsk.service.AddressService;
import com.bsk.service.impl.AddressServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet("/AddressServlet")
public class AddressServlet extends BaseServlet {
    private AddressService addressService = new AddressServiceImpl();
    public void addressList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获取当前登录用户
        User user = (User) request.getSession().getAttribute("user");

        //根据userId获取地址
        //模拟数据
        List<Address> addressList = addressService.getAddress(user.getUserId());
        //存储地址集合
        request.setAttribute("addressList",addressList);
        //请求转发
        request.getRequestDispatcher("address.jsp").forward(request,response);

    }

    public void save(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获取当前登录用户
        User user = (User) request.getSession().getAttribute("user");

        //获取所有的参数键值对
        Map<String, String[]> parameterMap = request.getParameterMap();
        //构建封装对象
        Address a = new Address();
        //参数封装到对象中
        BeanUtils.populate(a, parameterMap);

        //判断用户输入的地址是否存在
        List<Address> a1 = addressService.selectAll(user.getUserId(),a);
        if (a1.isEmpty()) {
            addressService.save(user.getUserId(),a);
            response.getWriter().print("1");
        }else {
            response.getWriter().print("0");
        }

    }

    public void del(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Integer addressId = Integer.valueOf(request.getParameter("addressId"));
        System.out.println(addressId);

        addressService.del(addressId);
        response.getWriter().print("1");
    }

    public void update(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获取当前登录用户
        User user = (User) request.getSession().getAttribute("user");

        //获取所有的参数键值对
        Map<String, String[]> parameterMap = request.getParameterMap();
        //构建封装对象
        Address a = new Address();
        //参数封装到对象中
        BeanUtils.populate(a, parameterMap);
        System.out.println(a);
        //判断用户输入的地址是否存在
        List<Address> a1 = addressService.selectAll(user.getUserId(),a);
        System.out.println(a1);
        if (a1.isEmpty()) {
            addressService.update(a);
            response.getWriter().print("1");
        }else {
            response.getWriter().print("0");
        }
    }
}
