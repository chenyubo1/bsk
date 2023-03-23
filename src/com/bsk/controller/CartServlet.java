package com.bsk.controller;

import com.bsk.dao.CartDao;
import com.bsk.dto.CartDto;
import com.bsk.po.Cart;
import com.bsk.po.User;
import com.bsk.service.CartService;
import com.bsk.service.impl.CartServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@WebServlet("/CartServlet")
public class  CartServlet extends BaseServlet {
    private CartService cartService = new CartServiceImpl();

    public void getCarts(HttpServletRequest request, HttpServletResponse response){

        try {
            User user = (User)request.getSession().getAttribute("user");

            Cart cart = new Cart();

            cart.setUserId(user.getUserId());

            List<CartDto> cartDtoList = cartService.getCarts(cart);

            //以json格式输出
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(response.getWriter(),cartDtoList);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void addCart(HttpServletRequest request, HttpServletResponse response){

        try {


            Map<String, String[]> parameterMap = request.getParameterMap();
            Cart cart = new Cart();
            BeanUtils.populate(cart,parameterMap);

            int update = cartService.addCart(cart);

            if(update > 0){
                response.getWriter().print("yes");
            }else{
                response.getWriter().print("no");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void updateCart(HttpServletRequest request, HttpServletResponse response){

        try {


            Map<String, String[]> parameterMap = request.getParameterMap();
            Cart cart = new Cart();
            BeanUtils.populate(cart,parameterMap);

            int update = cartService.update(cart);

            if(update > 0){
                response.getWriter().print("yes");
            }else{
                response.getWriter().print("no");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void delCart(HttpServletRequest request, HttpServletResponse response){

        try {

            User user = (User)request.getSession().getAttribute("user");
            Cart cart = new Cart();
            cart.setUserId(user.getUserId());
            System.out.println(cart);

            int update = cartService.clear(cart);

            if(update > 0){
                response.getWriter().print("yes");
            }else{
                response.getWriter().print("no");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
