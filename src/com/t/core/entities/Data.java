package com.t.core.entities;

public class Data {
	private String url;
	private String tag1;
	private String merchantName;
	private String branchName;
	private Integer rate;
	private String circle;
	private String address;
	private String phoneNumber;
	private String items;
	private String tag2;
	private String tag3;
	private Integer spending;
	private String discount;
	private String commentDetail;
	private String channel;
	private Integer dataId;
	
	public Integer getDataId() {
		return dataId;
	}
	public void setDataId(Integer dataId) {
		this.dataId = dataId;
	}
	public Data(){
		
	}
	public Data(String url, String tag1, String merchantName,
			String branchName, Integer rate, String circle, String address,
			String phoneNumber, String items, String tag2, String tag3,
			Integer spending, String discount, String commentDetail,
			String channel) {
		this.url = url;
		this.tag1 = tag1;
		this.merchantName = merchantName;
		this.branchName = branchName;
		this.rate = rate;
		this.circle = circle;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.items = items;
		this.tag2 = tag2;
		this.tag3 = tag3;
		this.spending = spending;
		this.discount = discount;
		this.commentDetail = commentDetail;
		this.channel = channel;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTag1() {
		return tag1;
	}
	public void setTag1(String tag1) {
		this.tag1 = tag1;
	}
	public String getMerchantName() {
		return merchantName;
	}
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public Integer getRate() {
		return rate;
	}
	public void setRate(Integer rate) {
		this.rate = rate;
	}
	public String getCircle() {
		return circle;
	}
	public void setCircle(String circle) {
		this.circle = circle;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getItems() {
		return items;
	}
	public void setItems(String items) {
		this.items = items;
	}
	public String getTag2() {
		return tag2;
	}
	public void setTag2(String tag2) {
		this.tag2 = tag2;
	}
	public String getTag3() {
		return tag3;
	}
	public void setTag3(String tag3) {
		this.tag3 = tag3;
	}
	public Integer getSpending() {
		return spending;
	}
	public void setSpending(Integer spending) {
		this.spending = spending;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	public String getCommentDetail() {
		return commentDetail;
	}
	public void setCommentDetail(String commentDetail) {
		this.commentDetail = commentDetail;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	
	

}
