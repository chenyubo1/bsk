package com.bsk.dao.impl;

import com.bsk.dao.CategoryDao;
import com.bsk.po.Address;
import com.bsk.po.Category;
import com.bsk.util.DataSourceUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class CategoryDaoImpl implements CategoryDao {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(DataSourceUtil.getDataSource());
    @Override
    public List<Category> getCategories() {
        String sql = "select * from t_category";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Category.class));

    }

    @Override
    public List<Category> getCategory(Category category) {
        String sql ="select * from t_category where category_name =?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Category.class),category.getCategoryName());
    }

    @Override
    public int save(Category category) {
        String sql ="insert into t_category(category_name) values (?) ";
        return jdbcTemplate.update(sql,category.getCategoryName());
    }

    @Override
    public void del(Category category) {
        String sql ="delete from t_category where category_id =?";
        jdbcTemplate.update(sql,category.getCategoryId());
    }

    @Override
    public int update(Category category) {
        String sql ="update t_category set category_name =? where category_id =?";
        return  jdbcTemplate.update(sql,category.getCategoryName(),category.getCategoryId());
    }


}
