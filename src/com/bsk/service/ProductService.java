package com.bsk.service;

import com.bsk.dto.ProductDto;
import com.bsk.po.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProducts(Integer id);
    List<ProductDto> productList(Product product, int pageNum, int pageSize);
    List<Product> getMenus(Product product);
    int updateProduct(Product product);
    int deleteProduct(Product product);
    int addProduct(Product product);
    List<Product> getProductList(Product product);
}
