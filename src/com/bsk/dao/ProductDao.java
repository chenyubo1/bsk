package com.bsk.dao;


import com.bsk.dto.ProductDto;
import com.bsk.po.Product;

import java.util.List;

public interface ProductDao {
    List<Product> getProducts(Integer id);
    List<Product> getMenus(Product product);
    List<ProductDto> productList(Product product, int pageNum, int pageSize);
    List<Product> getProductList(Product product);
    int updateProduct(Product product);
    int deleteProduct(Product product );
    int addProduct(Product product);
}
