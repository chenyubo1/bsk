package com.bsk.service;

import com.bsk.po.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getCategories();
    List<Category> getCategory(Category category);
    int save(Category category);
    int update( Category category);
    void del(Category category);

}
