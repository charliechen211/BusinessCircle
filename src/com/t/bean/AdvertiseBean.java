package com.t.bean;

import com.t.core.entities.Advertise;

//本类用于android端显示广告的详情，即用户订阅的商家推送的内容
/*
 * By liangyunlong
 */
public class AdvertiseBean {
	private Integer merchantId;
	private String merchantName;
	private String content;
	private String fromdate;
	private String todate;
	private String picture;
	public AdvertiseBean(){
		
	}
	public AdvertiseBean(Advertise a){
		this.merchantId = a.getMerchantId();
		this.content = a.getContent();
		this.fromdate = a.getFromdate();
		this.todate = a.getTodate();
		this.picture = a.getPicture();
	}
	public AdvertiseBean(Integer merchantId, String content, String fromdate,
			String todate, String picture) {
		super();
		this.merchantId = merchantId;
		this.content = content;
		this.fromdate = fromdate;
		this.todate = todate;
		this.picture = picture;
	}
	
	public String getMerchantName() {
		return merchantName;
	}
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	public Integer getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getFromdate() {
		return fromdate;
	}
	public void setFromdate(String fromdate) {
		this.fromdate = fromdate;
	}
	public String getTodate() {
		return todate;
	}
	public void setTodate(String todate) {
		this.todate = todate;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	
}
