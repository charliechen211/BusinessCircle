package com.t.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.Map.Entry;

import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.t.bean.MerchantBean;
import com.t.bean.SearchResultBean;
import com.t.core.dao.MerchantDao;
import com.t.core.dao.SubscriptionBCMerchantDao;
import com.t.core.dao.SubscriptionMerchantDao;
import com.t.core.dao.TagEntityDao;
import com.t.core.entities.Merchant;
import com.t.core.entities.TagEntity;
import com.t.service.interfaces.IMerchantService;
import com.t.utils.Constants;
import com.t.utils.Page;


@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class MerchantService implements IMerchantService {

	@Autowired
	private MerchantDao merchantDao; 
	@Autowired
	private TagEntityDao tagEntityDao;
	@Autowired
	private SubscriptionBCMerchantDao subscriptionMerchantDao;

	public String getName(int merchantId){
		return merchantDao.getName(merchantId);
	}
	//查看单个商家的详情，并返回用户是否订阅了该商家的广告。
	public MerchantBean fetchMerchantBean(Integer merchantId,Integer type,Integer userId){
		Merchant merchant = new Merchant();
		if(merchantDao.findByProperty("merchantId", merchantId).size() > 0)
			merchant = merchantDao.findByProperty("merchantId", merchantId).get(0);
		MerchantBean merchantBean = new MerchantBean(merchant);
		List<String> tags = new ArrayList<String>();
		List<TagEntity> tagEntity = tagEntityDao.getMerchantTags(merchantId);
		for(int i=0;i < tagEntity.size();i++){
			String t = tagEntity.get(i).getTagName();
			tags.add(t);
		}
		if(userId == null)
			merchantBean.setType(0);
		else if(subscriptionMerchantDao.getSubscriptionMerchant(userId, merchantId).size() > 0)
			merchantBean.setType(subscriptionMerchantDao.getSubscriptionMerchant(userId, merchantId).get(0).getType());
		else{
			merchantBean.setType(2);
		}
		merchantBean.setTagName(tags);
		return merchantBean;
	}
	//获得商铺集合
	@SuppressWarnings("unchecked")
	public List<MerchantBean> fetchMerchantBeans(Integer circleId,Integer pageId,Integer pageSize,Integer userId,Integer typeId){

		List<MerchantBean> merchantBeans = new ArrayList<MerchantBean>();
		List<Merchant> merchants = new ArrayList<Merchant>();
		Query q  = merchantDao.createQuery("from Merchant e where e.circle =:circleId and e.type =:typeId and e.checkStatus = 1");

//		Query q  = merchantDao.createQuery("from Merchant e where e.circle =:circleId and e.type =:typeId");
		q.setInteger("circleId",circleId);
		q.setInteger("typeId", typeId);
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


	public int shopAdd(Merchant shop) {
		return merchantDao.save(shop);
	}

	public List<Merchant> shopList() {
		return merchantDao.findAll();
	}

	//删除商家，同时更新评论，收藏表的数据
	public void shopDelete(String shopId) {
		merchantDao.delete(Integer.parseInt(shopId));
		
	}

	//通过名字查找商家,模糊查询
	@SuppressWarnings("unchecked")
	public List<Merchant> shopFindByMerchantName(String shopName,Vector<Integer> shopids) {
		Query q = merchantDao.createQuery("from Merchant e where e.merchantName like :names  and e.merchantId in (:ids)");
		q.setString("names","%"+shopName+"%" );
		q.setParameterList("ids", shopids);
		return q.list();	
	}

	//通过ID查找其商铺
	public Merchant shopFindByShopId(String shopId){
		return merchantDao.findUniqueByProperty("merchantId",Integer.parseInt(shopId)) ;
	}

	public void shopUpdate(Merchant shop) {
		//merchantDao.;
	}

	//通过商铺IDs获得商家集合
	@SuppressWarnings("unchecked")
	public List<Merchant> findByOwnerId(Vector<Integer> shopids) {	
		if(shopids.size()!=0){
			return merchantDao.createQuery("from Merchant e where e.merchantId in (:ids) and e.checkStatus =:status")
					.setParameterList("ids", shopids).setParameter("status", Constants.BC_PASS)
					.list();  //修改，增加一个状态位
		}else 
			return null;
	}



	public void updateMerchant(Merchant shop) {
		merchantDao.saveOrUpdate(shop);	

	}

	@Override
	public List<SearchResultBean> getShopListByKeyword(int circleId, int type,
			List<TagEntity> userTags, String keyword, int recommended) {
		// 处理消费历史

		List<SearchResultBean> ret= new ArrayList<SearchResultBean>();
		// 读取当前用户TAG
		List<Integer> interest = new ArrayList<Integer>();
		for (int i = 0; i < userTags.size(); i++) {
			interest.add(userTags.get(i).getTagId());
		}


		// 获取所有商铺信息
		List<Merchant> shoplist = merchantDao.findByCriteria(Restrictions.eq("circle", circleId),Restrictions.eq("type", type));

		String [] keys =keyword.split(" ");
		if (keys.length==0) {
			for (int i = 0; i < shoplist.size(); i++) {
				SearchResultBean sb = new SearchResultBean();
				sb.setShopBean(shoplist.get(i));
				sb.setWeight(0);
				//System.out.println(sb.getWeight());
				ret.add(sb);
			}
		}else {
			for (int i = 0; i < shoplist.size(); i++) {
				float count=0f;
				for (int j = 0; j < keys.length; j++) {
					List<String> tagsList  = new ArrayList<String>();
					List<TagEntity> shopTags = tagEntityDao.getMerchantTags(shoplist.get(i).getMerchantId());
					for (TagEntity shopTag : shopTags) {
						tagsList.add(shopTag.getTagName());
					}
					if(shoplist.get(i).getMerchantName().indexOf(keys[j])>=0
							||(shoplist.get(i).getAddress() != null && shoplist.get(i).getAddress().indexOf(keys[j])>=0)
							||(shoplist.get(i).getDescription() !=null && shoplist.get(i).getDescription().indexOf(keys[j])>=0)){
						count=count+1;
					}

					for (String tagString : tagsList) {
						if (tagString.contains(keys[j]))
						{
							count += 1;
						}
					}
				}
				SearchResultBean sb = new SearchResultBean();
				sb.setShopBean(shoplist.get(i));
				sb.setWeight((float)((count/keys.length)*0.4));
				System.out.println(shoplist.get(i).getMerchantName()+"\t"+sb.getWeight());
				ret.add(sb);
			}
		}


		// 开始算法
		List<TagEntity> tags = new ArrayList<TagEntity>();
		for (int i = 0; i < ret.size(); i++) {
			float weight1 = ret.get(i).getWeight();
			float weight = 0f;
			// 如果是1说明是原店铺或者和相关的店铺
			for (int j = 0; j < tags.size(); j++) {
				if (interest.contains(tags.get(j).getTagId())) {
					weight += (float)1/interest.size();
				}
			}

			System.out.println(ret.get(i).getShopBean().getMerchantName()+"  weight:"+weight);
			ret.get(i).setWeight(ret.get(i).getWeight()+weight);
			if (ret.get(i).getWeight()==0) {
				ret.get(i).setWeight(0.0000001f);
			}
		}

		for (int i = 0; i < interest.size(); i++) {
			System.out.println(interest.get(i));
		}

		return ret;
	}

	@Override
	public List<SearchResultBean> getShopListByShopId(int currentClickShopId,
			int circleId, int type, List<TagEntity> userTags, int recommended) {
		// 处理消费历史
		List<SearchResultBean> ret= new ArrayList<SearchResultBean>();
		// 读取当前用户TAG
		List<Integer> interest = new ArrayList<Integer>();
		for (int i = 0; i < userTags.size(); i++) {
			interest.add(userTags.get(i).getTagId());
		}

		// 获取所有商铺信息
		List<Merchant> shoplist = merchantDao.findByCriteria(Restrictions.eq("circle", circleId),Restrictions.eq("type", type));
		Merchant curShop = merchantDao.get(currentClickShopId);
		if (curShop!=null) {
			// 该商铺的TAGs
			List<TagEntity>keys = tagEntityDao.getMerchantTags(curShop.getMerchantId());
			List<Integer> tagIds = new ArrayList<Integer>();
			for (TagEntity key : keys) {
				tagIds.add(key.getTagId());
			}
			String shopName = curShop.getMerchantName();
			// 看商铺之间的关键字的相似度
			for (int i = 0; i < shoplist.size(); i++) {
				// 如果就是该家店铺直接设为最近
				if (shoplist.get(i).getMerchantId()== currentClickShopId)
				{
					SearchResultBean sb = new SearchResultBean();
					sb.setShopBean(shoplist.get(i));
					sb.setWeight(0.5f);
					ret.add(0,sb);
				}
				else{
					float weight = 0f;
					// 如果有部分名字重叠，可能是分店，给予较高相关性
					if(shoplist.get(i).getMerchantName().indexOf(shopName)>=0){
						weight+=0.8f;
					}
					else {
						for (int j = 0; j < tagIds.size(); j++) {
							List<TagEntity>entities = tagEntityDao.getMerchantTags(shoplist.get(i).getMerchantId());
							List<Integer> shopTagIds = new ArrayList<Integer>();
							for (TagEntity entity : entities) {
								shopTagIds.add(entity.getTagId());
							}
							if(shopTagIds.contains(tagIds.get(j))){
								weight+=(float)1/tagIds.size();
							}
						}
					}
					SearchResultBean sb = new SearchResultBean();
					sb.setShopBean(shoplist.get(i));
					sb.setWeight(weight*0.5f);
					System.out.println(shoplist.get(i).getMerchantName()+":"+sb.getWeight());
					ret.add(sb);
				}
			}
		}

		// 开始算法
		List<TagEntity> tags = new ArrayList<TagEntity>();
		for (int i = 0; i < ret.size(); i++) {
			float weight1 = ret.get(i).getWeight();
			float weight = 0f;
			// 如果是1说明是原店铺或者和相关的店铺
			for (int j = 0; j < tags.size(); j++) {
				if (interest.contains(tags.get(j).getTagId())) {
					weight += (float)1/interest.size();
				}
			}
			weight*=0.5f;
			ret.get(i).setWeight(weight+weight1);
			if (ret.get(i).getWeight()==0) {
				ret.get(i).setWeight(0.0000001f);
			}
		}
		for (int i = 0; i < interest.size(); i++) {
			System.out.println(interest.get(i));
		}

		return ret;
	}

	@Override
	public List<SearchResultBean> getShopList(int circleId, int type,
			List<TagEntity> userTags, int recommended) {
		Map <Integer,Integer> shopNames = new HashMap<Integer,Integer>();
		Map <String,Integer> names = new HashMap<String,Integer>();

		List<SearchResultBean> ret= new ArrayList<SearchResultBean>();

		// 读取当前用户TAG
		List<Integer> interest = new ArrayList<Integer>();
		for (int i = 0; i < userTags.size(); i++) {
			interest.add(userTags.get(i).getTagId());
		}

		List<Merchant> allShoplist = merchantDao.findByCriteria(Restrictions.eq("circle", circleId),Restrictions.eq("type", type));

		// 开始算法
		List<TagEntity> tags = new ArrayList<TagEntity>();
		for (int i = 0; i < allShoplist.size(); i++) {
			tags = tagEntityDao.getMerchantTags(allShoplist.get(i).getMerchantId());
			float weight = 0f;

			for (int j = 0; j < tags.size(); j++) {
				if (interest.contains(tags.get(j).getTagId())) {
					weight += (float)1/interest.size();
				}
			}

			SearchResultBean sb = new SearchResultBean();
			sb.setShopBean(allShoplist.get(i));
			sb.setWeight(weight);
			ret.add(sb);
		}
		// 打印信息
		Iterator <Entry<String,Integer>> it = names.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String,Integer> temp = it.next();
			System.out.println("去过:"+temp.getKey()+" "+temp.getValue()+"次。");
		}

		for (int i = 0; i < interest.size(); i++) {
			System.out.println(interest.get(i));
		}

		return ret;
	}


	//前台综合查询
	@SuppressWarnings("unchecked")
	public List<Merchant> integratedSearch(String shopid, String shopName,
			String address, Vector<Integer> shopids) {		 
		shopid = shopid.substring(0,shopid.length()-2);
		shopName = shopName.substring(0,shopName.length()-2);
		address = address.substring(0, address.length()-2);

		if(shopid.equals("")){	 
			Query q = merchantDao.createQuery("from Merchant e where e.merchantName like :name and " +
					"e.address like :address and e.merchantId in (:ids) and e.checkStatus =:status");
			q.setString("name","%"+shopName+"%" );
			q.setString("address","%"+address+"%" );
			q.setParameterList("ids", shopids);
			q.setParameter("status", Constants.BC_PASS);		
			return q.list();
		}else{
			Query q  = merchantDao.createQuery("from Merchant e where e.merchantId =:id and e.merchantId in (:ids) and e.checkStatus =:status");
			q.setInteger("id",Integer.parseInt(shopid));
			q.setParameterList("ids", shopids);
			q.setParameter("status", Constants.BC_PASS);
			return q.list();
		}	
	}

	//通过地址查询
	@SuppressWarnings("unchecked")
	public List<Merchant> shopFindByAddress(String address,Vector<Integer> shopids) {
		Query q = merchantDao.createQuery("from Merchant e where e.address like :addr  and e.merchantId in (:ids)");
		q.setString("addr","%"+address.trim()+"%" );
		q.setParameterList("ids", shopids);
		return q.list();
	}

	//获得待审核商铺列表
	public List<Merchant> getCheckShops() {
		Query q = merchantDao.createQuery("from Merchant e where e.checkStatus =:status");
		q.setString("status",Constants.BC_TOBE_CHECKED );
		return q.list();
	}
	
	@Override
	public int checkMerchant(String merId, String status) {
		try {
			Merchant mer = merchantDao.findUniqueByProperty("merchantId",Integer.parseInt(merId));
			mer.setCheckStatus(status);
			merchantDao.saveOrUpdate(mer);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Merchant> getAllShops() {
		Query q = merchantDao.createQuery("from Merchant e where e.checkStatus =:status");
		q.setString("status",Constants.BC_PASS );
		return q.list(); 
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Merchant> integratedSearchAll(String shopid, String shopName,
			String address) {
		shopid = shopid.substring(0,shopid.length()-2);
		shopName = shopName.substring(0,shopName.length()-2);
		address = address.substring(0, address.length()-2);

		if(shopid.equals("")){	 
			Query q = merchantDao.createQuery("from Merchant e where e.merchantName like :name and " +
					"e.address like :address and e.checkStatus =:status");
			q.setString("name","%"+shopName+"%" );
			q.setString("address","%"+address+"%" );
			q.setParameter("status", Constants.BC_PASS);
			return q.list();
		}else{
			Query q  = merchantDao.createQuery("from Merchant e where e.merchantId =:id and e.checkStatus =:status");
			q.setInteger("id",Integer.parseInt(shopid));
			q.setParameter("status", Constants.BC_PASS);
			return q.list();
		}	
	}
	
}
