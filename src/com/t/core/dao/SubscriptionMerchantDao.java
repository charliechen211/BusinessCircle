package com.t.core.dao;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.t.core.entities.SubscriptionMerchant;
import com.t.utils.BaseDao;
import com.t.utils.Constants;

@Repository
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class SubscriptionMerchantDao extends BaseDao<SubscriptionMerchant, Integer>{
	public SubscriptionMerchantDao(){
		this.entityClass = SubscriptionMerchant.class;
	}
	public List<SubscriptionMerchant> getSubscriptionMerchant(Integer userId,Integer merchantId)
	{
		return this.findByCriteria(Restrictions.eq("userId", userId),Restrictions.eq("merchantId", merchantId));
	}
	//拉取关注的商家
	public List<SubscriptionMerchant> getSubscriptedMerchant(Integer userId)
	{
		return this.findByCriteria(Restrictions.eq("userId", userId),Restrictions.eq("type", Constants.Follow));  
	}
}
