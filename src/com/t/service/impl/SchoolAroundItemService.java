package com.t.service.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.t.core.dao.SchoolAroundItemDao;
import com.t.bean.SchoolAroundItemBean;
import com.t.core.entities.Merchant;
import com.t.core.entities.SchoolAroundItem;
import com.t.service.interfaces.ISchoolAroundService;
import com.t.utils.Constants;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class SchoolAroundItemService implements ISchoolAroundService{

	@Autowired
	public SchoolAroundItemDao saiDao;

	@SuppressWarnings("unchecked")
	public SchoolAroundItemBean getItem(Integer itemId) {
		SchoolAroundItem item = saiDao.findUniqueByProperty("itemId", itemId);
		SchoolAroundItemBean bean = new SchoolAroundItemBean(item);
		return bean;
	}

	@SuppressWarnings("unchecked")
	public List<SchoolAroundItemBean> getList(Integer typeId,Integer objectId,Integer pageId, Integer pageSize){
		List<SchoolAroundItemBean> beanlist = new ArrayList<SchoolAroundItemBean>();
		List<SchoolAroundItem> items = saiDao.getItemList(typeId, objectId);

		Query q = saiDao.createQuery("from SchoolAroundItem e where e.typeId =:typeId and e.objectId=:objectId");
		q.setInteger("typeId",typeId);
		q.setInteger("objectId",objectId);
		q.setFirstResult(pageId*pageSize);
		q.setMaxResults(pageSize);
		items = q.list();
		int size = items.size();
		if(size>0){
			for(int i = 0;i<size;i++){
				SchoolAroundItemBean bean = new SchoolAroundItemBean(items.get(i));
				beanlist.add(bean);
			}
		}
		return beanlist;
	}

	@SuppressWarnings("unchecked")
	public void addItem(SchoolAroundItemBean bean){
		SchoolAroundItem item = new SchoolAroundItem();
		item.setItemName(bean.getItemName());
		item.setItemDescription(bean.getItemDescription());
		item.setItemLocation(bean.getItemLocation());
		item.setItemPic(bean.getItemPic());
		item.setItemPlus(bean.getItemPlus());
		item.setItemTel(bean.getItemTel());
		item.setLatitude(bean.getLatitude());
		item.setLongitude(bean.getLongitude());
		item.setObjectId(bean.getObjectId());
		item.setTypeId(bean.getTypeId());
		saiDao.save(item);
	}

	@Override
	public List<SchoolAroundItem> getAllShops() {
		// TODO Auto-generated method stub
		return saiDao.findAll();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SchoolAroundItem> IntegratedSearchAll(String itemId,
			String itemName, String itemLocation) {
		itemId = itemId.substring(0,itemId.length()-2);
		itemName = itemName.substring(0,itemName.length()-2);
		itemLocation = itemLocation.substring(0, itemLocation.length()-2);
		
		System.out.println("++++++++++++++"+itemId);

		if(itemId.equals("")||itemId.equals(null)){	 
			Query q = saiDao.createQuery("from SchoolAroundItem e where e.itemName like :name and " +
					"e.itemLocation like :address ");
			q.setString("name","%"+itemName+"%" );
			q.setString("address","%"+itemLocation+"%" );
			return q.list();
		}else{
			Query q  = saiDao.createQuery("from SchoolAroundItem e where e.itemId =:id ");
			q.setInteger("id",Integer.parseInt(itemId));
			return q.list();
		}
		}

	@Override
	public void updateItem(SchoolAroundItem item) {
		// TODO Auto-generated method stub
		saiDao.saveOrUpdate(item);
	}

	@Override
	public SchoolAroundItem finById(int itemId) {
		 
		return saiDao.findUniqueByProperty("itemId", itemId);
	}

}
