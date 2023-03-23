package com.bsk.dto;

import com.bsk.po.Product;

public class ProductDto extends Product {



    /**
     * 分类名称
     */
    private String categoryName;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
