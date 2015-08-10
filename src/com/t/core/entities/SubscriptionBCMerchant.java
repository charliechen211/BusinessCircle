package com.t.core.entities;
/**
 * 订阅商家
 * @author WangZhaoLi
 *
 */
public class SubscriptionBCMerchant {
	private Integer subscriptionId;
	private Integer userId;
	private Integer merchantId;
	private Integer type;

	public SubscriptionBCMerchant(){

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
 
	 

}
