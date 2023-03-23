package com.bsk.dao;

import com.bsk.po.Address;
import com.bsk.po.Category;

import java.util.List;

public interface CategoryDao {
    List<Category> getCategories();

//    查重
    List<Category> getCategory(Category category);
        /*添加*/
    int save(Category category);


    /**
     * 删除
     */
    void del(Category category);

    /**
     * 修改
     */
   int update(Category category);
}
