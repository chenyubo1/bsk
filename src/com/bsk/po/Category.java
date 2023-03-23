package com.bsk.po;

/**
 * 分类
 * @author 黑猫
 *
 */
public class Category {

	/**
	 * 分类编号
	 */
	private Integer categoryId;
	
	/**
	 * 分类名称
	 */
	private String categoryName;

	public Category() {}

	public Category(Integer categoryId, String categoryName) {
		this.categoryId = categoryId;
		this.categoryName = categoryName;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Override
	public String toString() {
		return "Category{" +
				"categoryId=" + categoryId +
				", categoryName='" + categoryName + '\'' +
				'}';
	}
}
