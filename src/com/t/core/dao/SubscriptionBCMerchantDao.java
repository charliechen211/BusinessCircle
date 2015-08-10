package com.t.core.dao;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.t.core.entities.SubscriptionBCMerchant;
import com.t.core.entities.SubscriptionMerchant;
import com.t.utils.BaseDao;
import com.t.utils.Constants;

@Repository
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class SubscriptionBCMerchantDao extends BaseDao<SubscriptionBCMerchant, Integer>{
	public SubscriptionBCMerchantDao(){
		this.entityClass = SubscriptionBCMerchant.class;
	}
	public List<SubscriptionBCMerchant> getSubscriptionMerchant(Integer userId,Integer merchantId)
	{
		return this.findByCriteria(Restrictions.eq("userId", userId),Restrictions.eq("merchantId", merchantId));
	}
	//拉取关注的商家
	public List<SubscriptionBCMerchant> getSubscriptedMerchant(Integer userId)
	{
		return this.findByCriteria(Restrictions.eq("userId", userId),Restrictions.eq("type", Constants.Follow));  
	}
}
