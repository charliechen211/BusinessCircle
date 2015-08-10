package com.t.bean;

import java.util.ArrayList;
import java.util.List;

import com.t.core.entities.Merchant;

public class MerchantBean implements Comparable<MerchantBean>{

	private String merchantName;
	private Integer merchantId;
	private String picture;
	private String telNumber;
	private String address;
	private Double averageConsume;
	private Double averageValue;
	private List<String> tagName;
	private Double distance;
	private Integer typeId;//商铺类型
	
	private Integer type;//是否订阅
	public MerchantBean() {
	}
	public MerchantBean(Merchant merchant){
		tagName = new ArrayList<String>();
		telNumber = merchant.getTelNumber();
		merchantId  = merchant.getMerchantId();
		merchantName = merchant.getMerchantName();
		address = merchant.getAddress();
		averageConsume = merchant.getAverageConsume();
		averageValue = merchant.getAverageValue();
		picture = merchant.getPicture();
		typeId = merchant.getType();
		type = merchant.getType();
	}
	
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Double getDistance() {
		return distance;
	}
	public void setDistance(Double distance) {
		this.distance = distance;
	}
	public Integer getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getTelNumber() {
		return telNumber;
	}

	public void setTelNumber(String telNumber) {
		this.telNumber = telNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Double getAverageConsume() {
		return averageConsume;
	}

	public void setAverageConsume(Double averageConsume) {
		this.averageConsume = averageConsume;
	}

	public Double getAverageValue() {
		return averageValue;
	}

	public void setAverageValue(Double averageValue) {
		this.averageValue = averageValue;
	}

	public List<String> getTagName() {
		return tagName;
	}

	public void setTagName(List<String> tagName) {
		this.tagName = tagName;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}

	public int compareTo(MerchantBean arg0) {
        return this.getDistance().compareTo(arg0.getDistance());
    }

}
