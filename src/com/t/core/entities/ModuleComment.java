package com.t.core.entities;

import java.sql.Timestamp;

public class ModuleComment {

	private Integer id;
	private Integer userId;
	private Integer objectId;  //帖子Id
	private String comment;
	private Timestamp timestamp;
	private Integer type;  //属于哪个模块
	private Integer delFlg;
	
	public ModuleComment() {}
	
	public ModuleComment(Integer id, Integer userId, Integer objectId, String comment,
			Timestamp timestamp, Integer type, Integer delFlg) {
		this.id = id;
		this.userId = userId;
		this.objectId = objectId;
		this.comment = comment;
		this.timestamp = timestamp;
		this.type = type;
		this.delFlg = delFlg;
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

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getDelFlg() {
		return delFlg;
	}

	public void setDelFlg(Integer delFlg) {
		this.delFlg = delFlg;
	}	

}
