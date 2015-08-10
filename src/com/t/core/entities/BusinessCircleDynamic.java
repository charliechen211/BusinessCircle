package com.t.core.entities;

import java.sql.Timestamp;

public class BusinessCircleDynamic {
	private Integer dynamicId;
	private Integer circleId;
	private String content;
	private Timestamp timestamp;
	private Integer userId;
	private Integer entityId;
	private Integer typeId;
	
	public BusinessCircleDynamic(){
		
	}
	public BusinessCircleDynamic(Integer dynamicId, Integer circleId,
			String content, Integer userId,
			Integer entityId, Integer typeId) {
		this.dynamicId = dynamicId;
		this.circleId = circleId;
		this.content = content;
		this.timestamp = null;
		this.userId = userId;
		this.entityId = entityId;
		this.typeId = typeId;
	}
	public Integer getDynamicId() {
		return dynamicId;
	}
	public void setDynamicId(Integer dynamicId) {
		this.dynamicId = dynamicId;
	}
	public Integer getCircleId() {
		return circleId;
	}
	public void setCircleId(Integer circleId) {
		this.circleId = circleId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getEntityId() {
		return entityId;
	}
	public void setEntityId(Integer entityId) {
		this.entityId = entityId;
	}
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	
	
	
}
