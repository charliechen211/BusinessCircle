package com.t.service.interfaces;

import java.util.List;
import java.util.Vector;

import com.t.bean.MerchantBean;
import com.t.bean.SearchResultBean;
import com.t.core.entities.Merchant;
import com.t.core.entities.TagEntity;

public interface IMerchantService {

	//android客户端行为
	public List<MerchantBean> fetchMerchantBeans(Integer circleId,Integer pageId,Integer pageSize,Integer userId,Integer typeId);
	public MerchantBean fetchMerchantBean(Integer merchantId,Integer type,Integer userId);
		
	//web商家端行为
	public int shopAdd(Merchant shop);
	public List<Merchant> shopList();
	
	//删除商家
	public void shopDelete(String shopId);
	
	//通过名字查找商家，模糊查询
	public List<Merchant> shopFindByMerchantName(String shopName,Vector<Integer> shopids);
	 
	//通过ID查找商家
	public Merchant shopFindByShopId(String shopId);
	
	//通过商家id查找其商铺列表
	public List<Merchant> findByOwnerId(Vector<Integer> shopids);
	
	public void shopUpdate(Merchant shop);
	
	//通过地址查找商家，模糊查询
	public List<Merchant> shopFindByAddress(String address,Vector<Integer> shopids);

	public void updateMerchant(Merchant shop);

	public List<SearchResultBean> getShopListByKeyword(int circleId,int type, List<TagEntity>userTags,String keyword,int recommended);
	public List<SearchResultBean> getShopListByShopId(int currentClickShopId,int circleId,int type, List<TagEntity>userTags,int recommended);
	public List<SearchResultBean> getShopList(int circleId, int type,List<TagEntity>userTags,int recommended);
	
	//综合查询
	public List<Merchant> integratedSearch(String shopid,String shopName,String address,Vector<Integer> shopids );
	public List<Merchant> integratedSearchAll(String shopid,String shopName,String address);
	public String getName(int merchantId);
	
	//获得所有通过的商铺
	public List<Merchant> getAllShops();
	
	//管理员获得待会审核商铺列表
	public List<Merchant> getCheckShops();
	//审核
	public int checkMerchant(String merId,String status);
	
}
