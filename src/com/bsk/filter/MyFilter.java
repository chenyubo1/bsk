package com.bsk.filter;

import com.bsk.controller.UserServlet;
import com.bsk.po.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class MyFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest re = (HttpServletRequest) req;
        HttpServletResponse res = (HttpServletResponse) resp;
        re.setCharacterEncoding("utf-8");
        res.setContentType("text/html;charset=UTF-8");
        User user = (User) re.getSession().getAttribute("user");
        if (user != null) {
            chain.doFilter(req, resp);
        } else {
            StringBuffer requestURL = re.getRequestURL();
            String methodName = re.getParameter("m");
            if (requestURL.indexOf("login.jsp") != -1
                    || requestURL.indexOf("register.jsp") != -1
                    || requestURL.indexOf(".js") != -1 || requestURL.indexOf(".css") != -1
                    || requestURL.indexOf(".png") != -1 || requestURL.indexOf(".jpg") != -1
                    || requestURL.indexOf("main.jsp") != -1 || requestURL.indexOf("menu.jsp") != -1
                    || requestURL.indexOf("MenuServlet") != -1 || requestURL.indexOf("top.jsp") != -1) {
                    if (requestURL.indexOf("member.jsp") != -1){
                    re.getRequestDispatcher("main.jsp").forward(re,res);
                }
                chain.doFilter(req, resp);
            } else if (methodName != null) {
                if ((methodName.contains("login") || methodName.contains("register"))||methodName.contains("log")){
                    chain.doFilter(req, resp);
                }else {
                    re.getRequestDispatcher("main.jsp").forward(re, res);
                }
            } else {
                re.getRequestDispatcher("main.jsp").forward(re, res);
            }
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
