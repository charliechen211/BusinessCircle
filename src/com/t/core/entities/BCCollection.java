package com.t.core.entities;

import java.sql.Timestamp;

public class BCCollection {
	private Integer CollectionId;
	private Integer entityId;
	private Integer entityType;
	private Integer userId;
	private Timestamp timestamp;
	
	public BCCollection()
	{
		
	}
	public BCCollection(Integer collectionId, Integer entityId,
			Integer entityType, Integer userId, Timestamp timestamp) {
		CollectionId = collectionId;
		this.entityId = entityId;
		this.entityType = entityType;
		this.userId = userId;
		this.timestamp = timestamp;
	}
	public Integer getCollectionId() {
		return CollectionId;
	}
	public void setCollectionId(Integer collectionId) {
		CollectionId = collectionId;
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
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

}
