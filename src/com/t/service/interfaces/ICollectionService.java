package com.t.service.interfaces;

import java.util.List;

import org.json.JSONException;

import com.t.bean.CircleDynamicBean;
import com.t.bean.ModuleObjectBean;

public interface ICollectionService {
	public Integer addCollection(Integer userId,Integer moduleId,/*Integer entityType,Integer entityId,*/Integer itemId);
	public Integer delCollection(Integer userId,Integer moduleId, Integer itemId);
	
	public List<ModuleObjectBean> fetchCollection(Integer userId);//我的大学
	
	public List<CircleDynamicBean> fetchBCCollection(Integer userId);//商圈
	public Integer addBCCollection(Integer userId,Integer entityId,Integer entityType) throws JSONException;//商圈
	public Boolean delBCCollection(Integer collectionId);//商圈 对现在的后台杂乱简直无语了 2015/2/4 11:05 am 还在实验室赶代码 lyl
}
 