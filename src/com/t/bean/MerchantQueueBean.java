package com.t.bean;

import java.util.List;

import com.t.core.entities.Queue;

public class MerchantQueueBean {
	private List<Queue> queueList;
	private List<TableOrderBean> tableOrderList;
	private int merchantId;
	private String merchantName;
	private String pic;
	
	public MerchantQueueBean(int merchantId, String merchantName, String pic, List<Queue> queueList, List<TableOrderBean> tableOrderList) {
		// TODO Auto-generated constructor stub
		this.merchantId = merchantId;
		this.queueList = queueList;
		this.tableOrderList = tableOrderList;
		this.merchantName = merchantName;
		this.pic = pic;
	}
	
	public List<Queue> getQueueList() {
		return queueList;
	}
	public void setQueueList(List<Queue> queueList) {
		this.queueList = queueList;
	}
	public List<TableOrderBean> getTableOrderList() {
		return tableOrderList;
	}
	public void setTableOrderList(List<TableOrderBean> tableOrderList) {
		this.tableOrderList = tableOrderList;
	}

	public int getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(int merchantId) {
		this.merchantId = merchantId;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	} 
	
}
