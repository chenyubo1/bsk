package com.bsk.dao.impl;

import com.bsk.dao.CartDao;
import com.bsk.dto.CartDto;
import com.bsk.po.Cart;
import com.bsk.util.DataSourceUtil;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class CartDaoImpl implements CartDao {

    private JdbcTemplate jdbcTemplate = new JdbcTemplate(DataSourceUtil.getDataSource());

    @Override
    public int insertCart(Cart cart) {
        return jdbcTemplate.update("insert into t_cart(product_id,product_num,user_id) values(?,?,?)",cart.getProductId(),cart.getProductNum(),cart.getUserId());
    }

    @Override
    public int updateCart1(Cart cart) {
        return jdbcTemplate.update("update t_cart set product_num = product_num + 1 where product_id = ? and user_id = ?",cart.getProductId(),cart.getUserId());
    }

    @Override
    public int updateCart2(Cart cart) {
        if(cart.getProductNum() == 0){
            return jdbcTemplate.update("delete from t_cart where cart_id = ?",cart.getCartId());
        }else{
            return jdbcTemplate.update("update t_cart set product_num = ? where cart_id = ?",cart.getProductNum(),cart.getCartId());
        }
    }

    @Override
    public int delCart(Cart cart) {
        return jdbcTemplate.update("delete from t_cart where user_id = ?",cart.getUserId());
    }


    @Override
    public Cart getCart(Cart cart) {

        try {
            return jdbcTemplate.queryForObject("select * from t_cart where product_id = ? and user_id = ?",new BeanPropertyRowMapper<>(Cart.class),cart.getProductId(),cart.getUserId());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<CartDto> getCarts(Cart cart) {

        String sql = "select c.cart_id,c.product_id,c.product_num,p.product_name,p.product_pic,p.product_price from t_cart c join t_product p on c.product_id = p.product_id where c.user_id = ?";
        try {
            return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(CartDto.class),cart.getUserId());
        } catch (Exception e) {
            return null;
        }

    }

}
