package com.t.core.entities;

import java.sql.Timestamp;

public class Collection {
	private Integer collectionId;
	private Integer userId;
	
	private Integer moduleId;  // 模块Id 左侧tab里九个最大的选项
	private Integer entityType;
	private Integer entityId;  
	private Integer itemId;   //收藏物
	
	private Timestamp timestamp;
	
	public Collection()
	{

	}
	public Collection(Integer collectionId,Integer userId,Integer moduleId, Integer entityType,Integer entityId,
			 Integer itemId, Timestamp timestamp) {
		this.collectionId = collectionId;
		this.moduleId = moduleId;
		this.entityId = entityId;
		this.entityType = entityType;
		this.userId = userId;
		this.itemId = itemId;
		this.timestamp = timestamp;
	}
	public Integer getCollectionId() {
		return collectionId;
	}
	public void setCollectionId(Integer collectionId) {
		this.collectionId = collectionId;
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
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	
	

}
