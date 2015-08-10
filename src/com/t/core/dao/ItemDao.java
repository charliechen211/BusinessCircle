package com.t.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.t.bean.ItemBean;
import com.t.core.entities.Item;
import com.t.utils.BaseDao;


/**
 * A data access object (DAO) providing persistence and search support for Item
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.t.model.Item
 * @author MyEclipse Persistence Tools
 */
@Repository
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ItemDao extends BaseDao<Item, Integer> {
	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(ItemDao.class);
	// property constants
	public static final String ITEM_NAME = "itemName";
	public static final String DESCRIPTION = "description";
	public static final String PRICE = "price";
	public static final String PICTURE = "picture";
	public static final String RATE = "rate";
	public static final String TYPE = "type";
 
	
	public ItemDao () {
		this.entityClass = Item.class;
	}
	@SuppressWarnings("unchecked")
	public List<Item> getItemsByIds(List<Integer> ids){
		List<Item> items = new ArrayList<Item>();
		if(ids.size() > 0)
			items = this.createQuery("from Item i where i.itemId in (:list)").setParameterList("list", ids).list();
		return items;
	}
}