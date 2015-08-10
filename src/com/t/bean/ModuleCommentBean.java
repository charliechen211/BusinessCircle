package com.t.bean;

/**
 * 发给前台的所有模块的评论
 */
import com.t.core.entities.ModuleComment;

public class ModuleCommentBean {

	private Integer id;
	private Integer userId;
	private Integer objectId;
	private String comment;
	private String timestamp;
	private Integer type;
	private String userName;
	private String picture;
	
	public ModuleCommentBean() {}

	public ModuleCommentBean(ModuleComment comment) {
		this.id = comment.getId();
		this.userId = comment.getUserId();
		this.objectId = comment.getObjectId();
		this.comment = comment.getComment();
		String time = comment.getTimestamp().toLocaleString();
		this.timestamp = time;
		this.type = comment.getType();
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getObjectId() {
		return objectId;
	}

	public void setObjectId(Integer objectId) {
		this.objectId = objectId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
