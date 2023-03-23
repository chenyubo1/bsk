package com.bsk.service.impl;

import com.bsk.dao.CategoryDao;
import com.bsk.dao.impl.CategoryDaoImpl;
import com.bsk.po.Category;
import com.bsk.service.CategoryService;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    private CategoryDao categoryDao = new CategoryDaoImpl();
    @Override
    public List<Category> getCategories() {
        return categoryDao.getCategories();
    }

    @Override
    public List<Category> getCategory(Category category) {
        return categoryDao.getCategory(category);
    }

    @Override
    public int save(Category category) {
        return categoryDao.save(category);
    }

    @Override
    public int update( Category category) {
        return categoryDao.update(category);
    }

    @Override
    public void del(Category category) {
        categoryDao.del(category);
    }
}
