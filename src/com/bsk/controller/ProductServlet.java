package com.bsk.controller;

import com.bsk.dto.ProductDto;
import com.bsk.po.Category;
import com.bsk.po.Product;
import com.bsk.po.User;
import com.bsk.service.CategoryService;
import com.bsk.service.ProductService;
import com.bsk.service.impl.CategoryServiceImpl;
import com.bsk.service.impl.ProductServiceImpl;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/ProductServlet")
public class ProductServlet extends BaseServlet {
    private ProductService productService = new ProductServiceImpl();
    private CategoryService categoryService = new CategoryServiceImpl();

    public void updateProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获取所有的参数键值对
        Map<String, String[]> parameterMap = request.getParameterMap();
        //构建封装对象
        Product p = new Product();
        //参数封装到对象中
        BeanUtils.populate(p, parameterMap);
        System.out.println(p);
        List<Product> products = productService.getProductList(p);
        System.out.println(products);
        if (products.size() == 0) {
            productService.updateProduct(p);

            response.getWriter().print("Y");
        }else {
            response.getWriter().print("N");
        }

    }
    public void addProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获取所有的参数键值对
        Map<String, String[]> parameterMap = request.getParameterMap();
        //构建封装对象
        Product p = new Product();
        //参数封装到对象中
        BeanUtils.populate(p, parameterMap);

        List<Product> products = productService.getProductList(p);
        System.out.println(products);
        if (products.size() == 0) {
            int i = productService.addProduct(p);
            response.getWriter().print("Y");
        }else {
            response.getWriter().print("N");
        }



    }

    public void productList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //搜索条件
        String productName = request.getParameter("productName") != null && !"".equals(request.getParameter("productName")) ? request.getParameter("productName") : null;
        Integer categoryId = request.getParameter("categoryId") != null && !"".equals(request.getParameter("categoryId")) ? Integer.parseInt(request.getParameter("categoryId")) : null;
        //定义页码号
        int pageNum = 1;
        if (request.getParameter("pageNum") != null) {
            pageNum = Integer.parseInt(request.getParameter("pageNum"));
            System.out.println(pageNum);
        }


        //每页数据量
        int pageSize = 2;

        //满足条件的总数据量
        int total = productService.productList(new Product(productName, categoryId), 0, 0).size();

        //总页码
        int pages = total % pageSize == 0 ? total / pageSize : total / pageSize + 1;

        //页码修正
        if (pageNum < pages) {
            pageNum=pages;
        }

        //当前页面需要的数据
        List<ProductDto> productDtos = productService.productList(new Product(productName, categoryId), pageNum, pageSize);
        System.out.println(productDtos);
        CategoryServiceImpl categoryService = new CategoryServiceImpl();
        List<Category> categories = categoryService.getCategories();


        //存储
        request.getSession().setAttribute("pageNum", pageNum);
        request.setAttribute("pages", pages);
        request.setAttribute("productDtos", productDtos);
        request.setAttribute("categores", categories);

        //请求转发
        request.getRequestDispatcher("product.jsp").forward(request, response);
    }

    public void updateStatus(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String productId = request.getParameter("productId");
        String productStatus = request.getParameter("productStatus");
        System.out.println(productId+""+productStatus);
        Product product = new Product();
        product.setProductId(Integer.valueOf(productId));
        product.setProductStatus(productStatus);
        System.out.println(product);
        int update = productService.updateProduct(product);
        if (update == 1) {
            response.getWriter().print("Y");
        }
    }
    public void detele(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String productId = request.getParameter("productId");
         Product product = new Product();
         product.setProductId(Integer.valueOf(productId));
        int i = productService.deleteProduct(product);
        if (i == 1) {
            response.getWriter().print("Y");
        }


    }

    /**
     * 餐品图片上传
     * @param request
     * @param response
     * @throws Exception
     */
    public void upload(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 创建一个DiskFileItemfactory工厂类
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 创建一个ServletFileUpload核心对象
        ServletFileUpload sfu = new ServletFileUpload(factory);
        // 解决上传文件名中文乱码
        sfu.setHeaderEncoding("utf-8");

        String filename = "";
        // 解析request对象
        try {
            FileItem fileItem = sfu.parseRequest(request).get(0);
            filename = uploadFile(fileItem);
            response.getWriter().print(filename);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            response.getWriter().print("");
        }

    }
    // 将上传文件表单项封装
    private String uploadFile(FileItem fileItem) {
        // 如果上传表单项
        // 得到文件输入流
        // 创建物理目录路径
        String realPath = this.getServletContext().getRealPath("/upload");
        // 根据该路径创建一个目录对象
        File dir = new File(realPath);
        if (!dir.exists()) {
            dir.mkdirs();// 创建一个指定的目录
        }
        // 得到上传的名子
        String filename = fileItem.getName();
        if (filename != null) {
            // 得到文件后缀
            String extend = filename.substring(filename.indexOf("."));
            // 重写生成一个唯一的文件名
            filename = System.currentTimeMillis() + extend;
        }
        // 上传文件,自动删除临时文件
        try {
            fileItem.write(new File(realPath, "/" + filename));
        } catch (Exception e) {
            filename = "";
        }

        return filename;
    }

}
