package com.t.bean;
/*
 * By liangyunlong
 */

public class OrderBean {
	private int orderId;
	private String userName;
    private String userTele;
    private String itemNames;
    private String orderTime;
    
    public OrderBean(){}
    
    public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserTele() {
		return userTele;
	}
	public void setUserTele(String userTele) {
		this.userTele = userTele;
	}
	public String getItemNames() {
		return itemNames;
	}
	public void setItemNames(String itemNames) {
		this.itemNames = itemNames;
	}
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String timestamp) {
		this.orderTime = timestamp;
	}
    
}
