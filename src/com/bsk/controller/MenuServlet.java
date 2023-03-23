package com.bsk.controller;

import com.bsk.dto.ProductDto;
import com.bsk.po.Category;
import com.bsk.po.Product;
import com.bsk.service.CategoryService;
import com.bsk.service.ProductService;
import com.bsk.service.impl.CategoryServiceImpl;
import com.bsk.service.impl.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/MenuServlet")
public class MenuServlet extends BaseServlet {
    private ProductService productService = new ProductServiceImpl();
    private CategoryService categoryService = new CategoryServiceImpl();
    public void menuList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获取所有上架的餐品
        Product p = new Product();
        p.setProductStatus("Y");
        if(request.getParameter("categoryId") != null){
            p.setCategoryId(Integer.parseInt(request.getParameter("categoryId")));
        }


        List<Product> productDtos = productService.getMenus(p);


        //获取分类列表
        List<Category> categories = categoryService.getCategories();

        //存储
        request.setAttribute("categoryId",p.getCategoryId());


        request.setAttribute("productDtos",productDtos);
        request.setAttribute("categories",categories);
        //请求转发
        request.getRequestDispatcher("menu.jsp").forward(request,response);
    }

}
