package com.t.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.t.bean.CircleDynamicBean;
import com.t.core.dao.BusinessCircleDynamicDao;
import com.t.core.dao.FriendDynamicDao;
import com.t.core.dao.ItemDao;
import com.t.core.dao.MerchantDao;
import com.t.core.dao.TagEntityDao;
import com.t.core.dao.UserDao;
import com.t.core.dao.UserFriendDao;
import com.t.core.dao.UserInfoDao;
import com.t.core.entities.BusinessCircleDynamic;
import com.t.core.entities.FriendDynamic;
import com.t.core.entities.Item;
import com.t.core.entities.Merchant;
import com.t.core.entities.TagEntity;
import com.t.core.entities.User;
import com.t.core.entities.UserFriend;
import com.t.core.entities.UserInfo;
import com.t.service.interfaces.ICircleDynamicService;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class CircleDynamicService implements ICircleDynamicService{
//	@Autowired
//	private UserDao userDao;
	@Autowired
	private UserInfoDao userInfoDao;
	@Autowired
	private BusinessCircleDynamicDao dynamicDao;
	@Autowired
	private ItemDao itemDao;
	@Autowired
	private MerchantDao merchantDao;
	@Autowired
	private FriendDynamicDao friendDynamicDao;
	@Autowired
	private UserFriendDao userFriendDao;
	@Autowired
	private TagEntityDao tagEntityDao;
	//将用户动态添加到商圈和好友动态，type=1代表商铺；type=2代表商品
	public int addCircleDynamic(Integer userId,Integer entityId, Integer typeId, String content,Integer shareTo)
	{
		BusinessCircleDynamic dynamic = new BusinessCircleDynamic();
		int merchantId =0; 
		if(typeId==1)
			merchantId = entityId;
		else if(typeId==2)
		{
			Item item = new Item();
			if(itemDao.findByProperty("itemId", entityId).size()==0)
				return -1;
			item = itemDao.findByProperty("itemId", entityId).get(0);
			merchantId = item.getMerchant();
		}
		if(merchantDao.findByProperty("merchantId", merchantId).size() == 0)
			return -1;
		Integer circleId = merchantDao.findByProperty("merchantId", merchantId).get(0).getCircle();
		dynamic.setCircleId(circleId);
		dynamic.setContent(content);
		dynamic.setEntityId(entityId);
		dynamic.setUserId(userId);
		dynamic.setTypeId(typeId);
		dynamic.setTimestamp(null);
		dynamicDao.save(dynamic);
		//addFriendDynamic(userId,entityId,typeId,content);   //发布到好友动态
		return 1;
	}
	@SuppressWarnings("unchecked")
	public List<CircleDynamicBean> fetchCircleDynamic(Integer circleId)
	{
		List<CircleDynamicBean> listBeans = new ArrayList<CircleDynamicBean>();
		List<BusinessCircleDynamic> lists = new ArrayList<BusinessCircleDynamic>();
		Query q = dynamicDao.createQuery("from BusinessCircleDynamic e where e.circleId =:id order by timestamp desc");
		q.setParameter("id", circleId);
		lists = q.list();
		//lists = dynamicDao.findByProperty("circleId", circleId);
		BusinessCircleDynamic dynamic = new BusinessCircleDynamic();
//		User user = new User();
		UserInfo userInfo = new UserInfo();
		Merchant merchant = new Merchant();
		Item item = new Item();
		for(int i=0;i < lists.size();i++)
		{
			CircleDynamicBean dynamicBean = new CircleDynamicBean();
			String temp;
			dynamic = lists.get(i);
//			if(userDao.findByProperty("userId", dynamic.getUserId()).size() > 0)
//				user = userDao.findByProperty("userId", dynamic.getUserId()).get(0);
			if(userInfoDao.findByProperty("userId", dynamic.getUserId()).size() > 0)
				userInfo = userInfoDao.findByProperty("userId", dynamic.getUserId()).get(0);
			dynamicBean.setUserName(userInfo.getNickname());
			dynamicBean.setEntityId(dynamic.getEntityId());
			dynamicBean.setContent(dynamic.getContent());
			temp = dynamic.getTimestamp().toString().replace('T',' ');
			dynamicBean.setTimestamp(temp.substring(0, temp.length()-2));
			dynamicBean.setEntityType(dynamic.getTypeId());
			
			//当前是商铺
			if(dynamic.getTypeId() == 1){
				if(merchantDao.findByProperty("merchantId", dynamic.getEntityId()).size() > 0)
					merchant = merchantDao.findByProperty("merchantId", dynamic.getEntityId()).get(0);
				dynamicBean.setMerchantName(merchant.getMerchantName());
				dynamicBean.setItemName("");
				dynamicBean.setRate(merchant.getAverageValue());
				dynamicBean.setPicture(merchant.getPicture());
				dynamicBean.setConsume(merchant.getAverageConsume());
				List<String> tags= new ArrayList<String>();
				List<TagEntity> tagEntity = tagEntityDao.getMerchantTags(dynamic.getEntityId());
				for(int j=0;j < tagEntity.size();j++){
					String t = tagEntity.get(j).getTagName();
					if (!tags.contains(t)) 
						tags.add(t);
				}
				dynamicBean.setTags(tags);
			}
			//当前是商品
			else if(dynamic.getTypeId() == 2)
			{
				if(itemDao.findByProperty("itemId", dynamic.getEntityId()).size() > 0)
					item = itemDao.findByProperty("itemId", dynamic.getEntityId()).get(0);
				if(merchantDao.findByProperty("merchantId", item.getMerchant()).size() > 0)
					merchant = merchantDao.findByProperty("merchantId", item.getMerchant()).get(0);
				dynamicBean.setMerchantName(merchant.getMerchantName());
				dynamicBean.setItemName(item.getItemName());
				dynamicBean.setRate(item.getRate());
				dynamicBean.setPicture(item.getPicture());
				dynamicBean.setConsume(item.getPrice());
				List<String> tags= new ArrayList<String>();
				List<TagEntity> tagEntity = tagEntityDao.getItemTags(dynamic.getEntityId());
				for(int j=0;j < tagEntity.size();j++){
					String t = tagEntity.get(j).getTagName();
					if (!tags.contains(t)) 
						tags.add(t);
				}
				dynamicBean.setTags(tags);
			}
			listBeans.add(dynamicBean);
		}
		return listBeans;
	}
	//userId是应该看到这条动态的用户，friendId是发布动态的好友,将该条动态发布给关注我的人
	public int  addFriendDynamic(Integer userId,Integer entityId, Integer typeId, String content)
	{
		List<UserFriend> userFriends = new ArrayList<UserFriend>();
		userFriends = userFriendDao.findByProperty("friendId", userId);
		for(UserFriend userFriend : userFriends) {
			FriendDynamic dynamic = new FriendDynamic();
			dynamic.setUserId(userFriend.getUserId()); //关注我的用户Id  
			dynamic.setFriendId(userId);  //发布该状态的用户
			dynamic.setContent(content);
			dynamic.setEntityId(entityId);
			dynamic.setEntityType(typeId);
			friendDynamicDao.save(dynamic);
		}
		return 1;
	}
	//查看我的分享
	public List<CircleDynamicBean> fetchMySharing(Integer userId){
		List<CircleDynamicBean> listBeans = new ArrayList<CircleDynamicBean>();
		List<BusinessCircleDynamic> lists = new ArrayList<BusinessCircleDynamic>();
		lists = dynamicDao.findByProperty("userId", userId);
		String temp;
		BusinessCircleDynamic dynamic = new BusinessCircleDynamic();
		Merchant merchant = new Merchant();
		Item item = new Item();
		for(int i=0;i < lists.size();i++)
		{
			CircleDynamicBean dynamicBean = new CircleDynamicBean();
			dynamic = lists.get(i);
			dynamicBean.setActionId(dynamic.getDynamicId());
			dynamicBean.setEntityId(dynamic.getEntityId());
			dynamicBean.setContent(dynamic.getContent());
			temp = dynamic.getTimestamp().toString().replace('T',' ');
			dynamicBean.setTimestamp( temp.substring(0, temp.length()-2));
			dynamicBean.setEntityType(dynamic.getTypeId());
			//当前是商铺
			if(dynamic.getTypeId() == 1){
				if(merchantDao.findByProperty("merchantId", dynamic.getEntityId()).size() > 0)
					merchant = merchantDao.findByProperty("merchantId", dynamic.getEntityId()).get(0);
				dynamicBean.setMerchantName(merchant.getMerchantName());
				dynamicBean.setItemName("");
				dynamicBean.setRate(merchant.getAverageValue());
				dynamicBean.setPicture(merchant.getPicture());
				dynamicBean.setConsume(merchant.getAverageConsume());
				List<String> tags= new ArrayList<String>();
				List<TagEntity> tagEntity = tagEntityDao.getMerchantTags(dynamic.getEntityId());
				for(int j=0;j < tagEntity.size();j++){
					String t = tagEntity.get(j).getTagName();
					if (!tags.contains(t)) 
						tags.add(t);
				}
				dynamicBean.setTags(tags);
				
			}
			//当前是商品
			else if(dynamic.getTypeId() == 2)
			{
				if(itemDao.findByProperty("itemId", dynamic.getEntityId()).size() > 0)
					item = itemDao.findByProperty("itemId", dynamic.getEntityId()).get(0);
				if(merchantDao.findByProperty("merchantId", item.getMerchant()).size() > 0)
					merchant = merchantDao.findByProperty("merchantId", item.getMerchant()).get(0);
				dynamicBean.setMerchantName(merchant.getMerchantName());
				dynamicBean.setItemName(item.getItemName());
				dynamicBean.setRate(item.getRate());
				dynamicBean.setPicture(item.getPicture());
				dynamicBean.setConsume(item.getPrice());
				List<String> tags= new ArrayList<String>();
				List<TagEntity> tagEntity = tagEntityDao.getItemTags(dynamic.getEntityId());
				for(int j=0;j < tagEntity.size();j++){
					String t = tagEntity.get(j).getTagName();
					if (!tags.contains(t)) 
						tags.add(t);
				}
				dynamicBean.setTags(tags);
			}
			listBeans.add(dynamicBean);
		}
		return listBeans;
	}
	
	//商圈：删除我的分享
	public Boolean delMySharing(Integer shareId) {
		try{
			dynamicDao.delete(shareId);
		}catch(Exception e) {
			e.printStackTrace();
			return new Boolean(false);
		}
		return new Boolean(true);
	}

}
