package com.t.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.t.bean.AdvertiseBean;
import com.t.core.dao.AdvertiseDao;
import com.t.core.dao.MerchantDao;
import com.t.core.dao.SubscriptionBCMerchantDao;
import com.t.core.entities.Advertise;
import com.t.core.entities.Merchant;
import com.t.core.entities.SubscriptionBCMerchant;
import com.t.service.interfaces.IAdvertiseService;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class AdvertiseService implements IAdvertiseService {
	
    @Autowired
    private AdvertiseDao adsDao;
    @Autowired
    private SubscriptionBCMerchantDao bcsubscriptionDao;
    @Autowired
    private MerchantDao merchantDao;
    //android 根据用户的id拉取其关注的商铺的广告
    public List<AdvertiseBean> getMyAdvertises(Integer userId){
    	List<AdvertiseBean> results = new ArrayList<AdvertiseBean>();
    	List<Advertise> advertises = new ArrayList<Advertise>();
    	List<Integer> merchantList = new ArrayList<Integer>();
    	List<SubscriptionBCMerchant> subscriptions = new ArrayList<SubscriptionBCMerchant>();
    	subscriptions = bcsubscriptionDao.getSubscriptedMerchant(userId);
    	for (SubscriptionBCMerchant subscription : subscriptions) {
    		merchantList.add(subscription.getMerchantId());
		}
    	advertises = adsDao.getAdvertisesByMerchantIdList(merchantList);
    	for (Advertise advertise : advertises){
    		AdvertiseBean bean = new AdvertiseBean(advertise);
    		Merchant merchant = merchantDao.findUniqueByProperty("merchantId", bean.getMerchantId());
    		bean.setMerchantName(merchant.getMerchantName());
    		results.add(bean);
    	}
    	return results;
    }
	
    /*添加*/
	public int advertiseAdd(Advertise ad) {
		return adsDao.save(ad);
	}
	
	/*删除*/
	public void deleteadById(int adid) {
		Advertise ad = adsDao.findUniqueByProperty("adId",adid);
		adsDao.delete(ad);
	}
	
    /*查找*/
	public Advertise findAdById(int adid) {	 
		return adsDao.findUniqueByProperty("adId",adid);
	}
	
    /*获得该商家广告集合*/
	@SuppressWarnings("unchecked")
	public List<Advertise> findMyAds(int merchantOwnerId) { 
		return adsDao.createQuery("from Advertise e where e.merchantOwnerId =:id").setParameter("id",merchantOwnerId).list();	
	}
	
    /*修改广告*/
	public void updateAdvertise(Advertise ad) {
		adsDao.saveOrUpdate(ad);	   
		
	}
	
	//前台综合查询
	@SuppressWarnings("unchecked")
	public List<Advertise> integratedSearch(String adverId, String merchantId,
			int merchantOwnerId) {
		  adverId = adverId.substring(0, adverId.length()-2);
		  merchantId = merchantId.substring(0,merchantId.length()-2);
		  
		  if(adverId.equals("")){//不是按照id
			  if(merchantId.equals("-1")){//未指定商铺
				  Query q = adsDao.createQuery("from Advertise e where e.merchantOwnerId =:merid ");
				  q.setInteger("merid", merchantOwnerId);
				  return q.list();
			  }else{
				  Query q = adsDao.createQuery("from Advertise e where e.merchantId =:merid ");
				  q.setInteger("merid", Integer.parseInt(merchantId));
				  return q.list();
			  }
		  }else{
			  Query q = adsDao.createQuery("from Advertise e where e.adId =:adid and e.merchantOwnerId =:id");//只能查自己的广告
			  q.setInteger("id", merchantOwnerId);
			  q.setInteger("adid", Integer.parseInt(adverId));
			  return q.list();
		  }
	}
    
}
