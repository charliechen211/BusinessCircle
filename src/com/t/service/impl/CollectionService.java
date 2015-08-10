package com.t.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.t.bean.CircleDynamicBean;
import com.t.bean.ModuleObjectBean;
import com.t.core.dao.AdvertiseDao;
import com.t.core.dao.BCCollectionDao;
import com.t.core.dao.CollectionDao;
import com.t.core.dao.ItemDao;
import com.t.core.dao.MerchantDao;
import com.t.core.dao.SchoolActivityDao;
import com.t.core.dao.ShInfoDao;
import com.t.core.dao.SchoolNewsDao;
import com.t.core.dao.SchoolAroundItemDao;
import com.t.core.dao.PartTimeJobDao;
import com.t.core.dao.TagEntityDao;
import com.t.core.entities.Advertise;
import com.t.core.entities.BCCollection;
import com.t.core.entities.Collection;
import com.t.core.entities.Item;
import com.t.core.entities.Merchant;
import com.t.core.entities.SchoolActivity;
import com.t.core.entities.SchoolAroundItem;
import com.t.core.entities.SchoolNews;
import com.t.core.entities.PartTimeJob;
import com.t.core.entities.ShLVInfo;
import com.t.core.entities.TagEntity;
import com.t.service.interfaces.ICollectionService;
import com.t.utils.Constants;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class CollectionService implements ICollectionService {
	@Autowired
	public CollectionDao collectionDao;
	@Autowired
	public SchoolAroundItemDao schoolAroundItemDao;
	@Autowired
	public SchoolNewsDao schoolnewsDao; 
	@Autowired 
	public PartTimeJobDao partTimeJobDao;

	@Autowired
	public AdvertiseDao adverDao;

	@Autowired
	public ShInfoDao shlvDao;
	@Autowired
	public SchoolActivityDao activityDao;
	
	
	@Autowired
	MerchantDao merchantDao;
	@Autowired
	ItemDao itemDao;
	@Autowired
	TagEntityDao tagEntityDao;
	
	@Autowired
	public BCCollectionDao BCCollecDao;


	//用户添加收藏
	//将Module类Id放入ModuleId 将最小Id放入itemId 如果有中间层 先entityId后entityType
	public Integer addCollection(Integer userId,Integer moduleId,/*Integer entityType,Integer entityId,*/Integer itemId){
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("moduleId", moduleId);
		/*paramMap.put("entityId", entityId);
		paramMap.put("entityType", entityType);*/
		//paramMap.put("userId",userId);
		paramMap.put("itemId", itemId);
		paramMap.put("userId", userId);

		if(collectionDao.findBySQL("select * from collection where " +
				"moduleId = :moduleId and itemId = :itemId and userId = :userId", paramMap).size() > 0){
			return -1;
		}
		else if(moduleId != null && itemId != null){

			//Collection collection = new Collection(null,userId,moduleId,entityType,entityId,itemId,null);
			Collection collection = new Collection();
			collection.setItemId(itemId);
			collection.setUserId(userId);
			collection.setModuleId(moduleId);

			return collectionDao.save(collection);
		}
		else
			return -2;
	}

	//用户查看自己的收藏列表  只显示ModuleName和具体名字或题目ItemName
	public List<ModuleObjectBean> fetchCollection(Integer userId){
		List<ModuleObjectBean> results = new ArrayList<ModuleObjectBean>();
		List<Collection> collections = new ArrayList<Collection>();
		collections = collectionDao.findByProperty("userId", userId);

		for(Collection ele:collections){
			ModuleObjectBean tempbean = new ModuleObjectBean();
			tempbean.setModuleId(ele.getModuleId());
			tempbean.setModuleName(Constants.Tabmap.get(ele.getModuleId()));

			//下面根据不同的Module从不同的表中获得相应的信息

			//校园周边的记录
			if(ele.getModuleId() == 1){
				SchoolAroundItem saitem = schoolAroundItemDao.findUniqueByProperty("itemId",ele.getItemId()); //如果是校园周边，则itemId一定不为空
				tempbean.setItemId(saitem.getItemId());
				tempbean.setTitle(saitem.getItemName());
				tempbean.setContent(saitem.getItemDescription());
				tempbean.setPicture(saitem.getItemPic());
				tempbean.setLatitude(saitem.getLatitude());
				tempbean.setLongitude(saitem.getLongitude());
				tempbean.setItemLocation(saitem.getItemLocation());
				//看是否有优惠信息
				Advertise ad = adverDao.findUniqueByProperty("adId", ele.getItemId());
				if(ad != null){
					tempbean.setAdverId(ad.getAdId());
					tempbean.setAdverFlag(true);
				}	
			}

			//校园资讯
			if(ele.getModuleId() == 2){
				SchoolNews news = schoolnewsDao.findUniqueByProperty("topicId",ele.getItemId());  
				tempbean.setItemId(ele.getItemId());
				tempbean.setTitle(news.getTitle());
				//tempbean.setContent(news.getContent());
				String time = String.valueOf(news.getDate());
				tempbean.setDate(time.substring(0, time.length()-2)); 
			}

			//兼职招聘
			if (ele.getModuleId() == 3){
				PartTimeJob ptj = partTimeJobDao.findUniqueByProperty("jobId",ele.getItemId());
				tempbean.setItemId(ptj.getJobId());
				tempbean.setTitle(ptj.getTitle());
				tempbean.setContent(ptj.getContent());
				String time = String.valueOf(ptj.getDate());
				tempbean.setDate(time.substring(0, time.length()-2));  
			}

			//二手或者鹊桥
			if (ele.getModuleId() == 4 || ele.getModuleId() == 5){
				ShLVInfo slv = shlvDao.findUniqueByProperty("shId",ele.getItemId());
				tempbean.setItemId(slv.getShId());
				tempbean.setTitle(slv.getTitle());
				tempbean.setContent(slv.getDescription());
				tempbean.setPicture(slv.getPicture());
				String time = String.valueOf(slv.getTimestamp());
				tempbean.setDate(time.substring(0, time.length()-2));  
			}

			//互动墙不能收藏

			//校园活动
			if (ele.getModuleId() == 7){
				SchoolActivity activity = activityDao.findUniqueByProperty("activityId",ele.getItemId());
				tempbean.setItemId(activity.getActivityId());
				tempbean.setTitle(activity.getTitle());
				tempbean.setContent(activity.getContent());
				tempbean.setPicture(activity.getPicture());
				String time = String.valueOf(activity.getDate());
				tempbean.setDate(time.substring(0, time.length()-2));  
			}

			results.add(tempbean);
		}
		return results;
		/*List<CollectionListBean> beans = new ArrayList<CollectionListBean>();
		List<Collection> collections = new ArrayList<Collection>();
		collections = collectionDao.findByProperty("userId", userId);
		int size = collections.size();
		Collection collection = new Collection();
		CollectionListBean bean = new CollectionListBean();
		ModuleTab moduleTab = new ModuleTab();
		for(int i=0;i < size;i++)
		{
			collection = collections.get(i);
			bean.setItemId(collection.getItemId());
			bean.setModuleId(collection.getModuleId());
			moduleTab = moduleTabDao.findUniqueByProperty("moduleId", collection.getModuleId());
			bean.setModuleName(moduleTab.getModuleName());
			if(collection.getModuleId() == 1){//校园周边
				Map<String,Object> paramMap = new HashMap<String,Object>();
				paramMap.put("itemId", collection.getItemId());
				paramMap.put("entityId", collection.getEntityId());
				paramMap.put("entityType",collection.getEntityType());
				List<SchoolAroundItem> schools = schoolAroundItemDao.findBySQL("select * from school_around_item where " +
						"itemId = : itemId and entityId = :entityId and entityType = :entityType", paramMap);
				SchoolAroundItem school = schools.get(0);
				bean.setItemName(school.getItemName());
				beans.add(bean);
			}
			else if (collection.getModuleId() == 2){//校园资讯 
				Map<String,Object> paramMap = new HashMap<String,Object>();
				paramMap.put("topicId", collection.getItemId());
				paramMap.put("topicType",collection.getEntityId());
				List<SchoolNews> newss = schoolnewsDao.findBySQL("select * from SchoolNews where " +
						"topicId = : topicId and topicType = :topicType ", paramMap);
				SchoolNews news = newss.get(0);
				bean.setItemName(news.getTitle());
				beans.add(bean);
			}
			else if (collection.getModuleId() == 3){//兼职招聘
				Map<String,Object> paramMap = new HashMap<String,Object>();
				paramMap.put("jobId", collection.getItemId());
				paramMap.put("jobType",collection.getEntityId());
				List<PartTimeJob> jobs = schoolnewsDao.findBySQL("select * from PartTimeJob where " +
						"jobId = : jobId and jobType = :jobType ", paramMap);
				PartTimeJob job = jobs.get(0);
				bean.setItemName(job.getTitle());
				beans.add(bean);
			}
            else if (collection.getModuleId() == 4){

			}
            else if (collection.getModuleId() == 5){

			}
            else if (collection.getModuleId() == 6){

			}
            else if (collection.getModuleId() == 7){

			}
			else
				return null;
		}
		return beans;*/
	}

	//删除某个收藏
	public Integer delCollection(Integer userId, Integer moduleId, Integer itemId) {

		if(moduleId == null || itemId == null)
			return -2;

		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("moduleId", moduleId);
		paramMap.put("itemId", itemId);
		paramMap.put("userId", userId);

		List<Collection> collections = collectionDao.findBySQL("select * from collection where " +
				"moduleId = :moduleId and itemId = :itemId and userId = :userId", paramMap);

		if(collections.size() == 0){
			return -1;
		}
		else {

			Collection collection = collections.get(0);			
			collectionDao.delete(collection);

			return 1;
		}

	}

	/*以下是智慧商圈的代码逻辑*/
	
	public List<CircleDynamicBean> fetchBCCollection(Integer userId){
		List<CircleDynamicBean> results = new ArrayList<CircleDynamicBean>();
		List<BCCollection> collections = new ArrayList<BCCollection>();
		collections = BCCollecDao.findByProperty("userId", userId);
		int size = collections.size();
		BCCollection c = new BCCollection();
		for(int i=0;i < size;i++)
		{
			CircleDynamicBean bean = new CircleDynamicBean();
			c = collections.get(i);
			bean.setActionId(c.getCollectionId());
			bean.setEntityId(c.getEntityId());
			bean.setEntityType(c.getEntityType());
			bean.setTimestamp(c.getTimestamp().toString().replace('T', ' '));
			// entityType=1收藏的餐饮商家
			if(c.getEntityType() == 1)
			{
				Merchant merchant = new Merchant();
				if(merchantDao.findByProperty("merchantId", c.getEntityId()).size() > 0)
					merchant = merchantDao.findByProperty("merchantId", c.getEntityId()).get(0);
				bean.setPicture(merchant.getPicture());
				bean.setRate(merchant.getAverageValue());
				bean.setConsume(merchant.getAverageConsume());
				bean.setMerchantName(merchant.getMerchantName());
				List<String> tags= new ArrayList<String>();
//				List<TagEntity> tagEntity = tagEntityDao.findByProperty("entityId", c.getEntityId());
				List<TagEntity> tagEntity = tagEntityDao.getMerchantTags(c.getEntityId());
				for(int j=0;j < tagEntity.size();j++){
					String t = tagEntity.get(j).getTagName();
					if (!tags.contains(t)) 
						tags.add(t);
				}
				bean.setTags(tags);
			}
			// entityType=2收藏的美食
			if(c.getEntityType() == 2)
			{
				Item item = new Item();
				if(itemDao.findByProperty("itemId",c.getEntityId()).size() > 0)
					item = itemDao.findByProperty("itemId",c.getEntityId()).get(0);
				bean.setPicture(item.getPicture());
				bean.setRate(item.getRate());
				bean.setConsume(item.getPrice());
				bean.setItemName(item.getItemName());
				List<String> tags= new ArrayList<String>();
//				List<TagEntity> tagEntity = tagEntityDao.findByProperty("entityId", c.getEntityId());
				List<TagEntity> tagEntity = tagEntityDao.getItemTags(c.getEntityId());
				for(int j=0;j < tagEntity.size();j++){
					String t = tagEntity.get(j).getTagName();
					if (!tags.contains(t)) 
						tags.add(t);
				}
				bean.setTags(tags);
			}
			results.add(bean);
		}
		return results;
	}

	@Override
	public Integer addBCCollection(Integer userId, Integer entityId,Integer entityType) {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("entityId", entityId);
		paramMap.put("entityType", entityType);
		paramMap.put("userId",userId);
		if(BCCollecDao.findBySQL("select * from bccollection where " +
				"entityId = :entityId and entityType = :entityType and userId =:userId", paramMap).size() > 0)
			return -1;
		BCCollection collection = new BCCollection(null,entityId,entityType,userId,null);
		return BCCollecDao.save(collection);
	}

	@Override
	public Boolean delBCCollection(Integer collectionId) {
		try{
			BCCollecDao.delete(collectionId); 
		}catch (Exception e) {
			e.printStackTrace();
			return new Boolean(false);
		}
		return new Boolean(true);
	}
	
	
}
