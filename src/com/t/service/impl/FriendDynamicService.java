package com.t.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sun.org.apache.xml.internal.serializer.ElemDesc;
import com.t.bean.CircleDynamicBean;
import com.t.bean.FriendDynamicBean;
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
import com.t.service.interfaces.IFriendDynamicService;
/*
 * By liangyunlong
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class FriendDynamicService implements IFriendDynamicService{
	@Autowired
	private TagEntityDao tagEntityDao;
	@Autowired
	private BusinessCircleDynamicDao dynamicDao;
	@Autowired
	private MerchantDao merchantDao;
	@Autowired
	private ItemDao itemDao;
//	@Autowired
//	private UserDao userDao;
	@Autowired
	private UserInfoDao userInfoDao;
	@Autowired
	private UserFriendDao userFriendDao;
	//用户查看自己的好友动态
	@SuppressWarnings("unchecked")
	public List<CircleDynamicBean> fetchFriendDynamic(Integer userId)
	{
		/*List<FriendDynamicBean> listBeans = new ArrayList<FriendDynamicBean>();
		List<FriendDynamic> lists = new ArrayList<FriendDynamic>();
		//lists = friendDynamicDao.findByProperty("userId", userId);
		FriendDynamic dynamic = new FriendDynamic();
		UserInfo user = new UserInfo();
		Merchant merchant = new Merchant();
		Item item = new Item();
		for(int i=0;i < lists.size();i++)
		{
			FriendDynamicBean dynamicBean = new FriendDynamicBean();
			dynamic = lists.get(i);
			if(userInfoDao.findByProperty("userId", dynamic.getFriendId()).size() > 0)
				user = userInfoDao.findByProperty("userId", dynamic.getFriendId()).get(0);
			dynamicBean.setUserName(user.getNickname());
			dynamicBean.setEntityId(dynamic.getEntityId());
			dynamicBean.setContent(dynamic.getContent());
			dynamicBean.setTimestamp(dynamic.getPublishTime());
			dynamicBean.setEntityType(dynamic.getEntityType());

			//当前是商铺
			if(dynamic.getEntityType() == 1){
				if(merchantDao.findByProperty("merchantId", dynamic.getEntityId()).size() > 0)
					merchant = merchantDao.findByProperty("merchantId", dynamic.getEntityId()).get(0);
				dynamicBean.setMerchantName(merchant.getMerchantName());
				dynamicBean.setItemName("");
				dynamicBean.setRate(merchant.getAverageValue());
				dynamicBean.setPicture(merchant.getPicture());
				dynamicBean.setConsume(merchant.getAverageConsume());
			}
			//当前是商品
			else if(dynamic.getEntityType() == 2)
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
			}
			listBeans.add(dynamicBean);
		}
		return listBeans;*/

		List<CircleDynamicBean> listBeans = new ArrayList<CircleDynamicBean>();
		List<BusinessCircleDynamic> lists = new ArrayList<BusinessCircleDynamic>();
		List<Integer> friendIds = new ArrayList<Integer>();//存放好友的id集合
		List<UserFriend> friendlist = userFriendDao.findByProperty("userId", userId);//看是否有好友

		if(friendlist.size()>0){   //确认有好友才继续查看他们的动态
			for(UserFriend ele:friendlist){
				friendIds.add(ele.getFriendId());
			}
			Query q = dynamicDao.createQuery("from BusinessCircleDynamic e where e.userId in (:ids) order by timestamp desc");
			q.setParameterList("ids", friendIds);
			lists = q.list();
			BusinessCircleDynamic dynamic = new BusinessCircleDynamic();
//			User user = new User();
			UserInfo userInfo = new UserInfo();
			Merchant merchant = new Merchant();
			Item item = new Item();
			String tempString;
			for(int i=0;i < lists.size();i++)
			{
				CircleDynamicBean dynamicBean = new CircleDynamicBean();
				dynamic = lists.get(i);
//				if(userDao.findByProperty("userId", dynamic.getUserId()).size() > 0)
//					user = userDao.findByProperty("userId", dynamic.getUserId()).get(0);
				if(userInfoDao.findByProperty("userId", dynamic.getUserId()).size() > 0)
					userInfo = userInfoDao.findByProperty("userId", dynamic.getUserId()).get(0);	
				dynamicBean.setUserName(userInfo.getNickname());
				dynamicBean.setEntityId(dynamic.getEntityId());
				dynamicBean.setContent(dynamic.getContent());
				tempString = dynamic.getTimestamp().toString().replace('T',' ');
				dynamicBean.setTimestamp(tempString.substring(0,tempString.length()-2));
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
			
		}
		
		return listBeans;
	}
}
