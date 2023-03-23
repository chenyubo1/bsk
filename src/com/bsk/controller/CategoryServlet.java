package com.bsk.controller;

import com.bsk.po.Category;
import com.bsk.po.Product;
import com.bsk.service.CategoryService;
import com.bsk.service.ProductService;
import com.bsk.service.impl.CategoryServiceImpl;
import com.bsk.service.impl.ProductServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet( "/CategoryServlet")
public class CategoryServlet extends BaseServlet {
   private CategoryService categoryService =new CategoryServiceImpl();
    public void categoryList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Category> categories = categoryService.getCategories();
        System.out.println(categories);
        request.setAttribute("categoryList", categories);
        //请求转发
        request.getRequestDispatcher("category.jsp").forward(request,response);
    }

    public void save(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String categoryName = request.getParameter("categoryName");
        Category category = new Category();
        category.setCategoryName(categoryName);

        List<Category> category1 = categoryService.getCategory(category);
        System.out.println(category1);
        if (category1.isEmpty()) {
            categoryService.save(category);
            response.getWriter().print("1");
        }else {
            response.getWriter().print("0");
        }
    }

    public void update(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Integer categoryId = Integer.valueOf(request.getParameter("categoryId"));
        String categoryName = request.getParameter("categoryName");
        Category category = new Category();
        category.setCategoryName(categoryName);
        category.setCategoryId(categoryId);
        List<Category> category1 = categoryService.getCategory(category);
        if (category1.isEmpty()) {
            categoryService.update(category);
            response.getWriter().print("1");
        }else {
            response.getWriter().print("0");
        }
    }
    public void del(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Integer categoryId = Integer.valueOf(request.getParameter("categoryId"));
        Category category = new Category();
        category.setCategoryId(categoryId);
        ProductService productService =new ProductServiceImpl();
        List<Product> products = productService.getProducts(categoryId);
        if (products.isEmpty()){
            categoryService.del(category);
            response.getWriter().print("1");
        }else {
            response.getWriter().print("0");
        }
    }
}
