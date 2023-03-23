package com.bsk.dto;

import com.bsk.po.Cart;


public class CartDto extends Cart {

    private String productName;

    private String productPic;

    private String productPrice;

    public CartDto() {

    }

    public CartDto(Integer cartId, Integer productId, int productNum, Integer userId, String productName, String productPic, String productPrice) {
        super(cartId, productId, productNum, userId);
        this.productName = productName;
        this.productPic = productPic;
        this.productPrice = productPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPic() {
        return productPic;
    }

    public void setProductPic(String productPic) {
        this.productPic = productPic;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }
}
