package com.bsk.dao.impl;

import com.bsk.dao.ProductDao;
import com.bsk.dto.ProductDto;
import com.bsk.po.Category;
import com.bsk.po.Product;
import com.bsk.util.DataSourceUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(DataSourceUtil.getDataSource());
    @Override
    public List<Product> getProducts(Integer id) {
        String sql = "select * from t_product where category_id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Product.class),id);
    }

    public List<Product> getMenus(Product product) {
        String sql = "select * from t_product where product_status = 'Y' ";
        ArrayList<Object> objects = new ArrayList<>();
        if (product.getCategoryId() != null&& !"".equals(product.getCategoryId())){
            sql+=" and category_id = ? ";
            objects.add(product.getCategoryId());
        }
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Product.class),objects.toArray());
    }

    @Override
    public List<ProductDto> productList(Product product, int pageNum, int pageSize) {
        String sql = "select p.*,c.* from t_product p join t_category c on p.category_id = c.category_id where 1=1";

        ArrayList objects = new ArrayList<>();
        if(product.getProductName() != null){
            sql += " and p.product_name like ?";
            objects.add("%"+product.getProductName()+"%");
        }
        if(product.getCategoryId() != null){
            sql += " and p.category_id = ?";
            objects.add(product.getCategoryId());
        }
        if(pageNum != 0 && pageSize != 0){
            sql += " limit "+(pageNum-1)*pageSize+","+pageSize;
        }
        try{
            return jdbcTemplate.query(sql, objects.toArray(), new BeanPropertyRowMapper<>(ProductDto.class));
        }catch (Exception e){
            return null;
        }

    }

    @Override
    public List<Product> getProductList(Product product) {
        String sql ="select * from t_product where  product_name = ? ";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Product.class),product.getProductName());
    }

    @Override
    public int updateProduct(Product product) {
        String sql ="update t_product set ";
        ArrayList<Object> objects = new ArrayList<>();
        if(product.getProductName()!= null){
            sql += "product_name =?,";
            objects.add(product.getProductName());
        }
        if(product.getProductPrice()!= null){
            sql += "product_price =?,";
            objects.add(product.getProductPrice());
        }
        if (product.getCategoryId()!= null) {
            sql+="category_id =?,";
            objects.add(product.getCategoryId());
        }
        if (product.getProductDescribe()!= null) {
            sql+="product_describe =?,";
            objects.add(product.getProductDescribe());
        }
        if (product.getProductPic()!= null) {
            sql+="product_pic =?,";
            objects.add(product.getProductPic());
        }
        if(product.getProductStatus()!= null){
            sql += "product_status = ?,";
            objects.add(product.getProductStatus());
        }
        sql = sql.substring(0,sql.length()-1)+" where product_id = ? ";
        objects.add(product.getProductId());
        System.out.println(sql);
        return jdbcTemplate.update(sql,objects.toArray());
    }

    @Override
    public int deleteProduct(Product product) {
       String sql = "delete from t_product where  product_id = ? ";
        return jdbcTemplate.update(sql,product.getProductId());
    }

    @Override
    public int addProduct(Product product) {
        String sql = "insert into t_product(product_name,product_price,product_pic,product_describe,category_id) values(?,?,?,?,?)";
        ArrayList<Object> objects = new ArrayList<>();
        objects.add(product.getProductName());
        objects.add(product.getProductPrice());
        objects.add(product.getProductPic());
        objects.add(product.getProductDescribe());
        objects.add(product.getCategoryId());
        System.out.println(sql);
        return jdbcTemplate.update(sql,objects.toArray());
    }


}
