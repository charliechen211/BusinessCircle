package com.t.bean;

public class QueueBean {
	private int orderNum;
	private int currentOrder;
	
	public QueueBean(int orderNum, int currentOrder){
		this.orderNum = orderNum;
		this.currentOrder = currentOrder;
	}
	
	public int getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	public int getCurrentOrder() {
		return currentOrder;
	}
	public void setCurrentOrder(int currentOrder) {
		this.currentOrder = currentOrder;
	}
}
