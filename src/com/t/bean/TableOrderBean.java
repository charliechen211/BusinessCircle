package com.t.bean;

public class TableOrderBean {
	private String tableType;
	private int order;
	
	public TableOrderBean(String tableType, int order) {
		// TODO Auto-generated constructor stub
		this.tableType = tableType;
		this.order = order;
	}
	
	public String getTableType() {
		return tableType;
	}
	public void setTableType(String tableType) {
		this.tableType = tableType;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	
	
}
