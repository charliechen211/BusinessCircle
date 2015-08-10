package com.t.service.interfaces;

import java.util.List;
import java.util.Vector;

import com.t.bean.ItemBean;
import com.t.core.entities.Item;

public interface IItemService {
	public List<Item> find(String item_name);
	
	public int itemAdd(Item it);
	
	//获得登录用户商铺中商品列表
	public List<Item> findMerchantItems(Vector<Integer> merchantIds);
	public List<ItemBean> findMerchantItemsById(Integer merchantId,String select);
	public ItemBean getItemByItemId(Integer itemId);
	//查找item用于查看详情
	public Item findItemById(String id);
	public void deleteItemById(int itemid);
	
	//根据item名查找“我的商铺”中商品集合,
	public List<Item> queryItemdByName(String name,Vector<Integer> myshopids);
	
	//更新数据
	public void updateItem(Item item);
	
	public List<Item> integratedSearch(String merchantid,String itemid,String itemname,Vector<Integer> shopids);
	
	//获得待审核商品列表
	public List<Item> itemCheck();
	 
}
