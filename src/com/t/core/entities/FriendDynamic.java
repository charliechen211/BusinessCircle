package com.t.core.entities;

import java.sql.Timestamp;

public class FriendDynamic {
	
	private Integer dynamicId;
	private String content;
	private Timestamp publishTime;
	private Integer userId;
	private Integer friendId;
	private Integer entityId;
	private Integer entityType;
	public FriendDynamic(){
		
	}
	public FriendDynamic(Integer dynamicId, String content,
			Timestamp publishTime, Integer userId, Integer friendId,
			Integer entityId, Integer entityType) {
		this.dynamicId = dynamicId;
		this.content = content;
		this.publishTime = publishTime;
		this.userId = userId;
		this.friendId = friendId;
		this.entityId = entityId;
		this.entityType = entityType;
	}
	public Integer getDynamicId() {
		return dynamicId;
	}
	public void setDynamicId(Integer dynamicId) {
		this.dynamicId = dynamicId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(Timestamp publishTime) {
		this.publishTime = publishTime;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getFriendId() {
		return friendId;
	}
	public void setFriendId(Integer friendId) {
		this.friendId = friendId;
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
	
	
	
}
