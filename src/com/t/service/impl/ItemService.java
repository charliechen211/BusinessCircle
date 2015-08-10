package com.t.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.t.bean.ItemBean;
import com.t.core.dao.ItemDao;
import com.t.core.dao.TagEntityDao;
import com.t.core.entities.Item;
import com.t.core.entities.TagEntity;
import com.t.service.interfaces.IItemService;
import com.t.utils.Constants;



@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ItemService implements IItemService {

	@Autowired
	private ItemDao itemDao;
	@Autowired
	private TagEntityDao tagEntityDao;
	@Override
	public List<Item> find(String item_name)
	{	
		return itemDao.findByProperty("itemName", item_name);
	}

	//通过商铺Ids找到其所有商品
	@SuppressWarnings("unchecked")
	public List<Item> findMerchantItems(Vector<Integer> merchantIds){
		return itemDao.createQuery("from Item e where e.merchant in (:ids)")
				.setParameterList("ids", merchantIds).list();  //添加一个状态位
	}

	//通过单个商铺Id查找其商品   当用户进入到商铺页面，返回到android客户端；
	@SuppressWarnings("unchecked")
	public List<ItemBean> findMerchantItemsById(Integer merchantId, String select){
		List<Item> list = new ArrayList<Item>();
		Query query = itemDao.createQuery("from Item i where i.merchant =:merchantId and i.itemName like :name");
		query.setString("name", "%"+select+"%");
		query.setInteger("merchantId", merchantId);
		list = query.list();
		List<ItemBean> resultList = new ArrayList<ItemBean>();
		for(Item item : list){
			ItemBean itemBean = new ItemBean(item);
			List<String> tags = new ArrayList<String>();
			List<TagEntity> tagEntity = tagEntityDao.getItemTags(item.getItemId());
			for(int i=0;i < tagEntity.size();i++){
				String t = tagEntity.get(i).getTagName();
					tags.add(t);
			}
			itemBean.setTags(tags);
			resultList.add(itemBean);
		}
		return resultList;
	}
	
	//查询单个商品详情
	public ItemBean getItemByItemId(Integer itemId){
		Item item = new Item();
		ItemBean itemBean = new ItemBean();
		if(itemDao.findByProperty("itemId", itemId).size() > 0){
			item = itemDao.findByProperty("itemId", itemId).get(0);
			List<TagEntity> tagEntity = tagEntityDao.getItemTags(item.getItemId());
			List<String> tags = new ArrayList<String>();
			for(int i=0;i < tagEntity.size();i++){
				String t = tagEntity.get(i).getTagName();
					tags.add(t);
			}
			itemBean = new ItemBean(item);
			itemBean.setTags(tags);
		}
		return itemBean;
	}
	public Item findItemById(String id) {
		return itemDao.findUniqueByProperty("itemId", Integer.parseInt(id));
	}

	@Override
	public void deleteItemById(int itemid) {
		Item it = itemDao.findUniqueByProperty("itemId",itemid);
		itemDao.delete(it);
	}

	@SuppressWarnings("unchecked")
	public List<Item> queryItemdByName(String name, Vector<Integer> myshopids) {
		Query q = itemDao.createQuery("from Item e where e.itemName like :names and e.merchant in (:ids)");
		q.setString("names","%"+name+"%");
		q.setParameterList("ids",myshopids);
		return q.list();	
	}

	@Override
	public void updateItem(Item item) {
		itemDao.saveOrUpdate(item);	     
	}

	@Override
	public int itemAdd(Item it) {
		return itemDao.save(it);
	}

	/*综合搜索*/
	@SuppressWarnings("unchecked")
	public List<Item> integratedSearch(String merchantid, String itemid,
			String itemname, Vector<Integer> shopids) {
		merchantid = merchantid.substring(0,merchantid.length()-2);
		itemid = itemid.substring(0,itemid.length()-2);
		itemname = itemname.substring(0, itemname.length()-2);

		//未输入商品id
		if(itemid.equals("")){	
			if(merchantid.equals("-1")){//未指定商铺
				Query q = itemDao.createQuery("from Item e where e.itemName like :name and e.merchant in (:ids)");
				q.setString("name","%"+itemname+"%" );
				q.setParameterList("ids", shopids);
				return q.list();
			}else{
				Query q = itemDao.createQuery("from Item e where e.itemName like :name and e.merchant =:id");
				q.setString("name","%"+itemname+"%" );
				q.setInteger("id", Integer.parseInt(merchantid));
				return q.list();
			}		
		}else{
			Query q  = itemDao.createQuery("from Item e where e.itemId =:id and e.merchant in (:ids)");
			q.setInteger("id",Integer.parseInt(itemid));
			q.setParameterList("ids", shopids);
			return q.list();
		}	

	}

	//获得待审核商品列表
	public List<Item> itemCheck() {
		Query q = itemDao.createQuery("from Item e where e.checkStatus =:status");
		q.setString("status",Constants.BC_TOBE_CHECKED);
		return q.list();
	}

}
