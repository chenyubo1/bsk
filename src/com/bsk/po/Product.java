package com.bsk.po;

/**
 * 餐品表
 * 
 * @author 黑猫
 */
public class Product {

	/**
	 * 餐品编号
	 */
	private Integer productId;

	/**
	 * 餐品名称
	 */
	private String productName;

	/**
	 * 餐品图片
	 */
	private String productPic;

	/**
	 * 餐品单价
	 */
	private String productPrice;

	/**
	 * 餐品描述
	 */
	private String productDescribe;

	/**
	 * 分类编号 外键
	 */
	private Integer categoryId;

	/**
	 * Y/N 上架/下架
	 */
	private String productStatus;

	public Product() {
	}

	public Product(Integer productId, String productName, String productPic, String productPrice, String productDescribe,
			Integer categoryId, String productStatus) {
		this.productId = productId;
		this.productName = productName;
		this.productPic = productPic;
		this.productPrice = productPrice;
		this.productDescribe = productDescribe;
		this.categoryId = categoryId;
		this.productStatus = productStatus;
	}

 	public Product(String productName, Integer categoryId) {
		this.productName = productName;
		this.categoryId = categoryId;
	}

    public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer produtId) {
		this.productId = produtId;
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

	public String getProductDescribe() {
		return productDescribe;
	}

	public void setProductDescribe(String productDescribe) {
		this.productDescribe = productDescribe;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}

	@Override
	public String toString() {
		return "Product [produtId=" + productId + ", productName=" + productName + ", productPic=" + productPic
				+ ", productPrice=" + productPrice + ", productDescribe=" + productDescribe + ", categoryId="
				+ categoryId + ", productStatus=" + productStatus + "]";
	}

}
