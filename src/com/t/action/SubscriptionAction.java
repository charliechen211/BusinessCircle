package com.t.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.t.service.interfaces.ISubscriptionService;
import com.t.utils.BaseAction;

public class SubscriptionAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	private Integer userId;
	private Integer merchantId;
	private Integer type;
	
	private Integer pageId;
	private Integer pageSize;

	
	@Autowired
	private ISubscriptionService subscriptionService;
	
	/**
	 * 对订阅进行增删管理,该操作是对School_around_item 表进行。
	 * 其中merchantId对应表中的一条数据的主键Id
	 * 从语义上理解,一条记录就是一个商家
	 * @return
	 */
	public String manageSubscription(){
		try{  		
			subscriptionService.manageSubscription(userId, merchantId, type);// 1-关注  2-取消关注
			result.put(STATE, SUCCESS);
		}catch(Exception e){ 	
			result.put(STATE, FAIL);
		}
		return SUCCESS;
	}
	
	
	public String manageBCSubscription(){
		try{  		
			subscriptionService.manageBCSubscription(userId, merchantId, type);// 1-关注  2-取消关注
			result.put(STATE, SUCCESS);
		}catch(Exception e){ 	
			result.put(STATE, FAIL);
		}
		return SUCCESS;
	}
	
	public String getFollowList(){
		try{  		
			result.put("result", subscriptionService.getFollowList(userId, pageId, pageSize));
			result.put(STATE, SUCCESS);
		}catch(Exception e){ 
			result.put(STATE, FAIL);
		}
		return SUCCESS;
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

	public Integer getPageId() {
		return pageId;
	}

	public void setPageId(Integer pageId) {
		this.pageId = pageId;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}


}
