package com.bsk.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/OrderServlet")
public class OrderServlet extends BaseServlet {

    public void orderList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String money = request.getParameter("money");


        System.out.println(money);

        request.setAttribute("money",money);
        //请求转发
        request.getRequestDispatcher("order_submit.jsp").forward(request,response);
    }
}
