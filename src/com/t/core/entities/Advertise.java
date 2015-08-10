package com.t.core.entities;

/**
 * Advertise entity. @author MyEclipse Persistence Tools
 */

public class Advertise implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer adId;
	//private String adName;
	private int merchantOwnerId;
	private int merchantId;
	private String content;
	private String fromdate;
	
	private String todate;
	private String picture;
	private Integer toSex;
	private Integer toAgelevel;
	private Integer toSalary;

	/** default constructor */
	public Advertise() {
	}


	public Integer getAdId() {
		return this.adId;
	}

	public void setAdId(Integer adId) {
		this.adId = adId;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFromdate() {
		return this.fromdate;
	}

	public void setFromdate(String fromdate) {
		this.fromdate = fromdate;
	}

	public String getTodate() {
		return this.todate;
	}

	public void setTodate(String todate) {
		this.todate = todate;
	}

	public String getPicture() {
		return this.picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Integer getToSex() {
		return this.toSex;
	}

	public void setToSex(Integer toSex) {
		this.toSex = toSex;
	}

	public Integer getToAgelevel() {
		return this.toAgelevel;
	}

	public void setToAgelevel(Integer toAgelevel) {
		this.toAgelevel = toAgelevel;
	}

	public Integer getToSalary() {
		return this.toSalary;
	}

	public void setToSalary(Integer toSalary) {
		this.toSalary = toSalary;
	}

	public int getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(int merchantId) {
		this.merchantId = merchantId;
	}
	
	public int getMerchantOwnerId() {
		return merchantOwnerId;
	}
	public void setMerchantOwnerId(int merchantOwnerId) {
		this.merchantOwnerId = merchantOwnerId;
	}

}