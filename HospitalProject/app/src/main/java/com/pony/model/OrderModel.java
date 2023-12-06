package com.pony.model;

public class OrderModel {

	private String orderId;
	private String orderUserName;
	private String orderTime;
	private String orderUserId;
	private String orderMessageId;
	private CourseModel coursemsg;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderUserName() {
		return orderUserName;
	}

	public void setOrderUserName(String orderUserName) {
		this.orderUserName = orderUserName;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public String getOrderUserId() {
		return orderUserId;
	}

	public void setOrderUserId(String orderUserId) {
		this.orderUserId = orderUserId;
	}

	public String getOrderMessageId() {
		return orderMessageId;
	}

	public void setOrderMessageId(String orderMessageId) {
		this.orderMessageId = orderMessageId;
	}

	public CourseModel getCoursemsg() {
		return coursemsg;
	}

	public void setCoursemsg(CourseModel coursemsg) {
		this.coursemsg = coursemsg;
	}

}
