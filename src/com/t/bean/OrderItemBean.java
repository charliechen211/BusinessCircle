package com.t.bean;

import java.sql.Timestamp;

import com.t.core.entities.Item;
/*
 * By liangyunlong
 */
public class OrderItemBean extends ItemBean{
	private Timestamp orderTime;
	private Integer number;
	public OrderItemBean(){
		super();
	}
	public OrderItemBean(Item item) {
		super(item);
	}

	public Timestamp getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Timestamp orderTime) {
		this.orderTime = orderTime;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	

}
