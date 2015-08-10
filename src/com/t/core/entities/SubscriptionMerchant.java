package com.t.core.entities;
/**
 * 订阅商家
 * @author WangZhaoLi
 *
 */
public class SubscriptionMerchant {
	private Integer subscriptionId;
	private Integer userId;
	private Integer merchantId;
	private Integer type;
	
	//以下是新加的属性
	private Integer itemId;  //小类里的具体某条记录

	public SubscriptionMerchant(){

	}
	public Integer getSubscriptionId() {
		return subscriptionId;
	}
	public void setSubscriptionId(Integer subscriptionId) {
		this.subscriptionId = subscriptionId;
	}
	public Integer getUserId() {  
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
 
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	 

}
