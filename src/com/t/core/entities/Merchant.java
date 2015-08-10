package com.t.core.entities;


/**
 * Merchant entity. @author MyEclipse Persistence Tools
 */

public class Merchant implements java.io.Serializable {


	private Integer merchantId;
	private Integer type;
	private String merchantName;
	private String branch;
	private String description;
	private String telNumber;
	private String address;
	private Double averageConsume;
	private Double averageValue;
	private String picture;
	private Integer circle;
	private Double longitude;
	private Double latitude;

	private  String checkStatus;//审核状态
	// Constructors

	/** default constructor */
	public Merchant() {
	}

	/** minimal constructor */
	public Merchant(String merchantName, String picture) {
		this.merchantName = merchantName;
		this.picture = picture;
	}

	/** full constructor */
	public Merchant(Integer type, String merchantName, String branch,
			String description, String telNumber, String address,
			Double averageConsume, Double averageValue, String picture) {
		this.type = type;
		this.merchantName = merchantName;
		this.branch = branch;
		this.description = description;
		this.telNumber = telNumber;
		this.address = address;
		this.averageConsume = averageConsume;
		this.averageValue = averageValue;
		this.picture = picture;

	}

	// Property accessors
	
	public Integer getCircle() {
		return circle;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public void setCircle(Integer circle) {
		this.circle = circle;
	}

	public Integer getMerchantId() {
		return this.merchantId;
	}

	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getMerchantName() {
		return this.merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getBranch() {
		return this.branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTelNumber() {
		return this.telNumber;
	}

	public void setTelNumber(String telNumber) {
		this.telNumber = telNumber;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Double getAverageConsume() {
		return this.averageConsume;
	}

	public void setAverageConsume(Double averageConsume) {
		this.averageConsume = averageConsume;
	}

	public Double getAverageValue() {
		return this.averageValue;
	}

	public void setAverageValue(Double averageValue) {
		this.averageValue = averageValue;
	}

	public String getPicture() {
		return this.picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}
	
}