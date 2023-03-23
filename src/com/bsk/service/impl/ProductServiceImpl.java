package com.bsk.service.impl;

import com.bsk.dao.ProductDao;
import com.bsk.dao.impl.ProductDaoImpl;
import com.bsk.dto.ProductDto;
import com.bsk.po.Product;
import com.bsk.service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    private ProductDao productDao =new ProductDaoImpl();


    @Override
    public List<Product> getProducts(Integer id) {
        return productDao.getProducts(id);
    }

    @Override
    public List<ProductDto> productList(Product product, int pageNum, int pageSize) {
        return productDao.productList(product,pageNum,pageSize);
    }

    @Override
    public List<Product> getMenus(Product product) {
        return productDao.getMenus(product);
    }

    @Override
    public int updateProduct(Product product) {
        return productDao.updateProduct(product);
    }

    @Override
    public int deleteProduct(Product product) {
        return productDao.deleteProduct(product);
    }

    @Override
    public int addProduct(Product product) {
        return productDao.addProduct(product);
    }

    @Override
    public List<Product> getProductList(Product product) {
        return productDao.getProductList(product);
    }
}
