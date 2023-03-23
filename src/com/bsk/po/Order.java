package com.bsk.po;

/**
 * 订单管理
 * @author 黑猫
 *
 */
public class Order {

	/**
	 * 订单号
	 */
	private Integer orderId;
	
	/**
	 * 生成时间
	 */
	private String addTime;
	
	/**
	 * 修改时间
	 */
	private String updateTime;
	
	/**
	 * 用户编号 外键
	 */
	private Integer userId;
	
	/**
	 * 送餐地址
	 */
	private String addressDetails;
	
	/**
	 * 订单状态 1/2/3/(已下单/配送中/已完成)，默认 1
	 */
	private Integer orderType;
	
	/**
	 * 开始时间
	 */
	private String startTime;
	
	/**
	 * 结束时间
	 */
	private String endTime;

	public Order() {}

	public Order(Integer orderId, String addTime, String updateTime, Integer userId, String addressDetails,
			Integer orderType, String startTime, String endTime) {
		this.orderId = orderId;
		this.addTime = addTime;
		this.updateTime = updateTime;
		this.userId = userId;
		this.addressDetails = addressDetails;
		this.orderType = orderType;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getAddressDetails() {
		return addressDetails;
	}

	public void setAddressDetails(String addressDetails) {
		this.addressDetails = addressDetails;
	}

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", addTime=" + addTime + ", updateTime=" + updateTime + ", userId="
				+ userId + ", addressDetails=" + addressDetails + ", orderType=" + orderType + ", startTime="
				+ startTime + ", endTime=" + endTime + "]";
	}
	
}
