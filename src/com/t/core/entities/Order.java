package com.t.core.entities;

import java.sql.Timestamp;

public class Order {
	private Integer orderId;
	private Integer merchantId;
	private Integer cartId;
	private Integer userId;
	private Integer status;
	private Timestamp orderTime;
	
	public Order(){}
	public Order(Integer orderId, Integer merchantId, Integer cartId,
			Integer userId, Integer status, Timestamp orderTime) {
		this.orderId = orderId;
		this.merchantId = merchantId;
		this.cartId = cartId;
		this.userId = userId;
		this.status = status;
		this.orderTime = orderTime;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}
	public Integer getCartId() {
		return cartId;
	}
	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Timestamp getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Timestamp orderTime) {
		this.orderTime = orderTime;
	}
	
}
