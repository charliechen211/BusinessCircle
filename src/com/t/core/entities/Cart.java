package com.t.core.entities;

import java.sql.Timestamp;

public class Cart {
	private Integer cartId;
	private Integer userId;
	private Integer status;
	private Timestamp cartTime;
	public Cart(){}
	public Cart(Integer cartId, Integer userId, Integer status,
			Timestamp cartTime) {
		this.cartId = cartId;
		this.userId = userId;
		this.status = status;
		this.cartTime = cartTime;
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
	public Timestamp getCartTime() {
		return cartTime;
	}
	public void setCartTime(Timestamp cartTime) {
		this.cartTime = cartTime;
	}
	
}
