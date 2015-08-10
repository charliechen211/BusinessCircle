package com.t.core.entities;

import java.sql.Time;

public class MerchantTableInfo implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer mtiId;
	private Integer merchantId;
	private String tableType;
	private String description;
	private String startTime;
	private String endTime;
	
	public MerchantTableInfo(){
		
	}
	
	public MerchantTableInfo(int mtiId){
		this.mtiId = mtiId;
	}
	
	public MerchantTableInfo(int merchantId, String tableType, 
			String description, String starTime, String endTime){
		this.merchantId = merchantId;
		this.tableType = tableType;
		this.description = description;
		this.startTime = starTime;
		this.endTime = endTime;
	}
	
	public Integer getMtiId() {
		return mtiId;
	}
	public void setMtiId(Integer mtiId) {
		this.mtiId = mtiId;
	}
	public Integer getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}
	public String getTableType() {
		return tableType;
	}
	public void setTableType(String tableType) {
		this.tableType = tableType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	
}
