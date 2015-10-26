package com.t.service.impl;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mongodb.BasicDBObject;
import com.mongodb.CommandResult;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.sun.org.apache.xml.internal.serializer.ElemDesc;
import com.t.bean.MerchantBean;
import com.t.bean.UserBean;
import com.t.core.dao.MerchantDao;
import com.t.core.dao.TagEntityDao;
import com.t.core.dao.UserFriendDao;
import com.t.core.dao.UserInfoDao;
import com.t.core.entities.Merchant;
import com.t.core.entities.TagEntity;
import com.t.core.entities.UserInfo;
import com.t.service.interfaces.ISearchMerchantService;
import com.t.test.MongoClient;
import com.t.utils.Constants;
import com.t.utils.Distances;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class SearchMerchantService implements ISearchMerchantService {

	@Autowired
	private MerchantDao merchantDao;
	@Autowired
	private TagEntityDao tagEntityDao;
	@Autowired
	private UserInfoDao userInfoDao;
	@Autowired
	private UserFriendDao userFriendDao;


	//根据用户的输入搜索相应的好友
	@SuppressWarnings("unchecked")
	public List<UserBean> searchUser(String content,Integer pageId,Integer pageSize){
		List<UserBean> userBeans = new ArrayList<UserBean>();
		List<UserInfo> users = new ArrayList<UserInfo>();
		String sql = "SELECT userInfo.* FROM UserInfo userInfo INNER JOIN User user ON userInfo.uid=user.user_id "+
				"WHERE (userInfo.nickname like :content "+
				"OR user.mobile_phone like :content)" + 
				" limit  :pageFrom , :pageSize";
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("content", "%"+content+"%");
		paramMap.put("pageFrom",pageId*pageSize);
		paramMap.put("pageSize",pageSize);
		users = userInfoDao.findBySQL(sql, paramMap);
		for (UserInfo userInfo : users) {
			UserBean bean = new UserBean();
			Integer userId = userInfo.getUserId();
			List<String> tags = new ArrayList<String>();
			List<TagEntity> tagEntitys= new ArrayList<TagEntity>();
			tagEntitys = tagEntityDao.getUserTags(userId);
			for (TagEntity tagEntity : tagEntitys) {
				tags.add(tagEntity.getTagName());
			}
			bean.setUserId(userInfo.getUserId());
			bean.setUserName(userInfo.getNickname());
			bean.setPicture(userInfo.getPicture());
			bean.setPoint(userInfo.getPoint());
			bean.setFanNum(userFriendDao.findByProperty("userId", userId).size());
			bean.setFollowNum(userFriendDao.findByProperty("friendId", userId).size());
			bean.setTags(tags);
			userBeans.add(bean);
		}
		return userBeans;
	}
	//根据商家名搜索商家 ，排序结果按照商家得分递减
	@SuppressWarnings("unchecked")
	public List<MerchantBean> searchMerchant(String content,Integer pageId,Integer pageSize,Integer circleId,Integer typeId){

		/*List<MerchantBean> merchantBeans = new ArrayList<MerchantBean>();
		List<Merchant> merchants = new ArrayList<Merchant>();
		String  sql = "SELECT DISTINCT  m.* FROM Merchant m left JOIN Tag_Entity t ON m.mid=t.eid "+
		"WHERE m.type =:typeId AND m.circle =:circleId AND ( m.merchant_name like :content "+
		"OR m.address like :content OR t.tagName like :content)" + 
		" limit  :pageFrom , :pageEnd";
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("content", "%"+content+"%");
		paramMap.put("typeId",typeId);
		paramMap.put("circleId",circleId);
		paramMap.put("pageFrom",pageId*pageSize);
		paramMap.put("pageEnd",pageSize);
		merchants = merchantDao.findBySQL(sql, paramMap);
		int size = merchants.size();
		for(int i =0;i < size;i++){
			MerchantBean bean = new MerchantBean(merchants.get(i));
			merchantBeans.add(bean);
			}		
		return merchantBeans;*/

		List<MerchantBean> merchantBeans = new ArrayList<MerchantBean>();
		List<Merchant> merchants = new ArrayList<Merchant>();


		//新加代码，插入mongodb数据
		//		List<Merchant> merchants4monogodb = new ArrayList<Merchant>();
		//		/*Query q1  = merchantDao.createQuery("FROM Merchant m WHERE m.merchantId >:small and m.merchantId <:big");
		//		q1.setInteger("small",740);
		//		q1.setInteger("big",756);*/
		//		merchants4monogodb = merchantDao.findByCriteria(Restrictions.gt("merchantId", 740),Restrictions.lt("merchantId",756));
		//		//merchants4monogodb = q1.list();
		//		System.out.println("***********************"+merchants4monogodb.size());
		//		//MongoClient.insertMongodb(merchants4monogodb);

		Query q  = merchantDao.createQuery("FROM Merchant m WHERE m.circle =:circleId and m.type =:typeId and m.checkStatus = 1 and (m.merchantName like :content "+
				"OR m.address like :content)");
		q.setInteger("circleId",circleId);
		q.setInteger("typeId", typeId);
		q.setString("content","%"+content.trim()+"%");
		q.setFirstResult(pageId*pageSize);
		q.setMaxResults(pageSize);
		merchants = q.list();
		int size = merchants.size();
		for(int i =0;i < size;i++){
			MerchantBean bean = new MerchantBean(merchants.get(i));
			merchantBeans.add(bean);
		}		
		return merchantBeans;	
	}

	/**
	 *基于地理位置的推荐
	 */
	@SuppressWarnings("unchecked")
	public List<MerchantBean> searchBasedLocation(Double longitude,Double latitude,Double distance,Integer pageId,Integer pageSize){
		/* String HOST_ADDR = "192.168.0.121";
		 int PORT = 27017;
		 String COLLECTION_NAME = "merchant";
		 String DB_NAME = "test";
		 int R = 6371;//KM*/
		List<MerchantBean> merchantBeans = new ArrayList<MerchantBean>();
		Merchant merchant = new Merchant();
		List<Merchant> allShops = merchantDao.findAll();
		List<Merchant> resultList = new ArrayList<Merchant>();
		//获得要求距离范围内的商家
		for(Merchant ele :allShops){  
			if(Distances.computeDistance(ele.getLongitude(),ele.getLatitude(),longitude,latitude)<=distance){
				resultList.add(ele);
			}
		}

		if(resultList.size()>0){
			for(int j=0;j<resultList.size();j++){
				merchant = merchantDao.findUniqueByProperty("merchantId",resultList.get(j).getMerchantId());
				if(merchant.getCheckStatus().equals(Constants.BC_PASS)) {
					List<TagEntity> tagEntity = tagEntityDao.findByProperty("entityId",resultList.get(j).getMerchantId());
					List<String> tags = new ArrayList<String>();
					for(int k=0;k < tagEntity.size();k++){
						String t = tagEntity.get(k).getTagName();
						if (!tags.contains(t)) {
							tags.add(t);
						}
					}
					MerchantBean bean = new MerchantBean(merchant);
					bean.setTagName(tags);
					bean.setDistance(Distances.computeDistance(resultList.get(j).getLongitude(),resultList.get(j).getLatitude(),longitude,latitude));
					merchantBeans.add(bean);
				}
			}

			Collections.sort(merchantBeans);

			int i = pageId*pageSize;
			int size = (pageId+1)*pageSize < merchantBeans.size() ? (pageId+1)*pageSize : merchantBeans.size();

			return merchantBeans.subList(i, size);
		}else 
			return null;

		/*	for (;i < size; i++) {
			merchant = merchantDao.findUniqueByProperty("merchantId",resultList.get(i).getMerchantId());
			List<TagEntity> tagEntity = tagEntityDao.findByProperty("entityId",resultList.get(i).getMerchantId());
			List<String> tags = new ArrayList<String>();
			for(int j=0;j < tagEntity.size();j++){
				String t = tagEntity.get(j).getTagName();
				if (!tags.contains(t)) {
					tags.add(t);
				}
			}
			MerchantBean bean = new MerchantBean(merchant);
			bean.setTagName(tags);
			bean.setDistance(GetDistance(resultList.get(i).getLongitude(),resultList.get(i).getLatitude(),longitude,latitude));
			merchantBeans.add(bean);		
		}*/



		/*try {
			Mongo mongo = new Mongo(HOST_ADDR, PORT);
				DB db = mongo.getDB(DB_NAME);
				DBCollection coll = db.getCollection(COLLECTION_NAME); 
				System.out.print(coll.getCount());
				DBCursor cursor = coll.find();   

				while(cursor.hasNext()) {   
				  DBObject object = cursor.next();   
				  System.out.println(object);   
				}   

				double[] coordinate = new double[2];
				Double maxDistance = distance/6371.0;
				coordinate[0]=latitude;
				coordinate[1]=longitude;
				DBObject cmd = new BasicDBObject();
				cmd.put("geoNear", COLLECTION_NAME);
				cmd.put("near", coordinate);
				cmd.put("maxDistance", maxDistance);
				cmd.put("spherical", true);
				cmd.put("distanceMultiplier", 6371);
				CommandResult result= db.command(cmd);
				List<BasicDBObject> resultList = (List<BasicDBObject>)result.get("results");



			int i = pageId*pageSize;
			int size = (pageId+1)*pageSize < resultList.size() ? (pageId+1)*pageSize : resultList.size();
			for (;i < size; i++) {
				BasicDBObject bo = new BasicDBObject();
					BasicDBObject o = new BasicDBObject();
					bo = resultList.get(i);
					o = (BasicDBObject)bo.get("obj");

				merchant = merchantDao.findUniqueByProperty("merchantId",o.get("_id"));
				List<TagEntity> tagEntity = tagEntityDao.findByProperty("entityId",o.get("_id"));
				List<String> tags = new ArrayList<String>();
				for(int j=0;j < tagEntity.size();j++){
					String t = tagEntity.get(j).getTagName();
					if (!tags.contains(t)) {
						tags.add(t);
					}
				}
				MerchantBean bean = new MerchantBean(merchant);
				bean.setTagName(tags);
				bean.setDistance(bo.getDouble("dis")+0.05);
				merchantBeans.add(bean);
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (MongoException e) {
			e.printStackTrace();
		}*/

	}


	public static void main(String[] args) {

	}

}

