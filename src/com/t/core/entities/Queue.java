package com.t.core.entities;

import java.sql.Timestamp;

public class Queue implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer queueId;
	private Integer merchantId;
	private Integer userId;
	private String tableType;
	private Integer orderNum;
	private String status;
	private Timestamp startTime;
	private Timestamp endTime;
	
	public Queue(){
		
	}
	
	public Queue(int merchantId, int userId, String tableType){
		this.merchantId = merchantId;
		this.userId = userId;
		this.tableType = tableType;
	}
	
	public Queue(int merchantId, int userId, String tableType, int orderNum){
		this.merchantId = merchantId;
		this.userId = userId;
		this.tableType = tableType;
		this.orderNum = orderNum;
	}
	
	public Queue(int merchantId, int userId, String tableType, int orderNum, Timestamp startTime){
		this.merchantId = merchantId;
		this.userId = userId;
		this.tableType = tableType;
		this.orderNum = orderNum;
		this.startTime = startTime;
	}
	
	public Queue(int merchantId, int userId, String tableType, int orderNum, String status, Timestamp startTime){
		this.merchantId = merchantId;
		this.userId = userId;
		this.tableType = tableType;
		this.orderNum = orderNum;
		this.status = status;
		this.startTime = startTime;
	}
	
	public String getTableTypeString() {
		return tableType;
	}

	public void setTableTypeString(String tableType) {
		this.tableType = tableType;
	}
	
	public Integer getQueueId() {
		return queueId;
	}
	public void setQueueId(Integer queueId) {
		this.queueId = queueId;
	}
	public Integer getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Timestamp getStartTime() {
		return startTime;
	}
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	public Timestamp getEndTime() {
		return endTime;
	}
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public String getTableType() {
		return tableType;
	}

	public void setTableType(String tableType) {
		this.tableType = tableType;
	}
	
	
}
