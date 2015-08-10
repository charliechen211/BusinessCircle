package com.t.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.t.core.dao.SchoolAroundItemDao;
import com.t.core.dao.SubscriptionBCMerchantDao;
import com.t.core.dao.SubscriptionMerchantDao;
import com.t.core.entities.SchoolAroundItem;
import com.t.core.entities.SubscriptionBCMerchant;
import com.t.core.entities.SubscriptionMerchant;
import com.t.service.interfaces.ISubscriptionService;
 
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class SubscriptionService implements ISubscriptionService{
	@Autowired
	private SubscriptionMerchantDao subscriptionDao;
	@Autowired
	private SchoolAroundItemDao  niDao;
	
	@Autowired
	private SubscriptionBCMerchantDao subbcDao;
	

	//管理用户对广告的订阅和取消订阅--智慧商圈
	public Integer manageBCSubscription(Integer userId,Integer merchantId,Integer type){
		if(userId == null || merchantId == null)
			return -1;
		List<SubscriptionBCMerchant> subscriptions = new ArrayList<SubscriptionBCMerchant>();
		SubscriptionBCMerchant subscriptionMerchant = new SubscriptionBCMerchant();
		subscriptions = subbcDao.getSubscriptionMerchant(userId, merchantId);
		if(subscriptions.size() > 0)
			subscriptionMerchant = subscriptions.get(0);
		subscriptionMerchant.setUserId(userId);
		subscriptionMerchant.setMerchantId(merchantId);
		subscriptionMerchant.setType(type);
		subbcDao.saveOrUpdate(subscriptionMerchant);
		return 1;
	}

	//管理用户对广告的订阅和取消订阅(delFlag  1-关注  2-取消关注)--我的大学
	public void manageSubscription(Integer userId, Integer itemId,Integer delFlag) {

		SubscriptionMerchant subscriptionMerchant = new SubscriptionMerchant();

		subscriptionMerchant.setUserId(userId);
		subscriptionMerchant.setMerchantId(itemId);
		subscriptionMerchant.setType(delFlag);
		subscriptionDao.saveOrUpdate(subscriptionMerchant);	
	}

	//获得订阅列表
	@SuppressWarnings("unchecked")
	@Override
	public List<SchoolAroundItem> getFollowList(Integer userId,Integer pageId,Integer pageSize) { 
		List<SchoolAroundItem> followList = new ArrayList<SchoolAroundItem>();
		try{
			String sql = "SELECT * FROM school_around_item as saitem left join subscription_merchant as submer "
					+ "ON saitem.itemId = submer.itemId "+
					"WHERE (submer.userId =:userId) limit :pageFrom, :pageSize";
			Map<String,Object> paramMap = new HashMap<String, Object>();
			paramMap.put("userId", userId);
			paramMap.put("pageFrom",pageId*pageSize);
			paramMap.put("pageSize",pageSize);
			followList = niDao.findBySQL(sql, paramMap);
			return followList;
		}catch (Exception e)
		{
			e.printStackTrace();
			return null; 
		}
	}




}
