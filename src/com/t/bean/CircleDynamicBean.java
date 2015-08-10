package com.t.bean;

import java.sql.Timestamp;
import java.util.List;
//本类为商圈动态中动态详情的显示
public class CircleDynamicBean {
	private Integer actionId;
	private Integer entityId;
	private String userName;
	private String content;
	private Double rate;
	private String picture;
	private String timestamp;
	private String itemName;
	private String merchantName;
	private Double consume;
	private List<String> tags;
	private Integer entityType;
	public CircleDynamicBean(){
		
	}
	public CircleDynamicBean(Integer entityId, String userName, String content,
			Double rate, String picture, String timestamp, String itemName,
			String merchantName,Double consume,Integer entityType) {
		this.entityId = entityId;
		this.userName = userName;
		this.content = content;
		this.rate = rate;
		this.picture = picture;
		this.timestamp = timestamp;
		this.itemName = itemName;
		this.merchantName = merchantName;
		this.consume = consume;
		this.entityType = entityType;
	}
	
	
	public Integer getActionId() {
		return actionId;
	}
	public void setActionId(Integer actionId) {
		this.actionId = actionId;
	}
	public Integer getEntityType() {
		return entityType;
	}
	public void setEntityType(Integer entityType) {
		this.entityType = entityType;
	}
	public String getMerchantName() {
		return merchantName;
	}
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	public Double getConsume() {
		return consume;
	}
	public void setConsume(Double consume) {
		this.consume = consume;
	}
	public Integer getEntityId() {
		return entityId;
	}
	public void setEntityId(Integer entityId) {
		this.entityId = entityId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

}
