package com.t.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.t.bean.CircleDynamicBean;
import com.t.bean.ModuleObjectBean;
import com.t.service.interfaces.ICollectionService;
import com.t.utils.BaseAction;

public class CollectionAction extends BaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer entityId;
	private Integer entityType;
	private Integer moduleId;
	private Integer userId;
	private Integer itemId;
	private Integer collectionId;
	private List<ModuleObjectBean> colletions; 
	
	private List<CircleDynamicBean> bccollections;

	@Autowired
	private ICollectionService collectionServe;

	/**
	 * 我的大学--添加收藏
	 * 只需要userId，Moduleid,itemId 即可（谁收藏了哪个模块的哪个记录）
	 */
	public String addCollection(){
		
		Integer r = collectionServe.addCollection(userId,moduleId,/* entityType,entityId,*/ itemId);
		if(r == -1)
		{
			result.put("state","exist");
		}
		else if(r == -2){
			result.put("state", "fault");
		}
		else{
			result.put("state",SUCCESS);
		}
		return SUCCESS;
	}
	
	
	/**
	 * 智慧商圈--添加收藏
	 * @return
	 */
	public String addBCCollection(){
		
		Integer r = collectionServe.addBCCollection(userId, entityId, entityType);
		if(r == -1)
		{
			result.put("state","exist");
		}else{
			result.put("state",SUCCESS);
		}
		return SUCCESS;
	}
	
	/**
	 * 我的大学删除收藏
	 * @return
	 */
	
	public String delCollection() {
		
		Integer r = collectionServe.delCollection(userId, moduleId, itemId);
		if(r == -1) {
			result.put("state", NOTEXIST);
		}
		else if(r == -2) {
			result.put("state", FAIL);
		}
		else {
			result.put("state", SUCCESS);
		}
		return SUCCESS;
	}
	
	/**
	 * 智慧商圈收藏
	 * @return
	 */
	public String delBCCollection() {
		result.put(STATE, collectionServe.delBCCollection(collectionId));
		return SUCCESS;
	}

	//我的大学--拉取用户的收藏列表
	public String fetchCollection(){
		colletions = collectionServe.fetchCollection(userId);
		if(colletions.size() ==0){
			result.put(STATE, FAIL);
		}
		else{
			result.put("results",colletions);
			result.put(STATE, SUCCESS);
		}
		return SUCCESS;
	}
	
	//智慧商圈--拉取用户的收藏列表
	public String fetchBCCollection(){
		bccollections = collectionServe.fetchBCCollection(userId);
		if(bccollections.size() ==0){
			result.put(STATE, FAIL);
		}
		else{
			result.put("results",bccollections);
			result.put(STATE, SUCCESS);
		}
		return SUCCESS;
	}


	public Integer getEntityId() {
		return entityId;
	}
	public void setEntityId(Integer entityId) {
		this.entityId = entityId;
	}
	public Integer getEntityType() {
		return entityType;
	}
	public void setEntityType(Integer entityType) {
		this.entityType = entityType;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getModuleId() {
		return moduleId;
	}

	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}


	public Integer getCollectionId() {
		return collectionId;
	}


	public void setCollectionId(Integer collectionId) {
		this.collectionId = collectionId;
	}
	
	
}
