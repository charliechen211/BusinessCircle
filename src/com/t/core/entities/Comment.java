package com.t.core.entities;

import java.sql.Timestamp;

public class Comment {
	private Integer commentId;

	private Integer entityId;
	private Integer entityType;
	private Integer userId;
	private String content;
	private Double rate1;
	private Double rate2;
	private Double rate3;
	private Double consume;
	private Timestamp timestamp;
	public Comment() {
	}
	public Comment(Integer commentId,Integer entityId,Integer entityType,Integer userId,String content,Double rate1,Double rate2,Double rate3,Timestamp timestamp,Double consume){
		this.entityId = entityId;
		this.entityType = entityType;
		this.userId = userId;
		this.content = content;
		this.rate1 = rate1;
		this.rate2 = rate2;
		this.rate3 = rate3;
		this.timestamp = timestamp;
		this.commentId = commentId; 
		this.consume = consume;
	}
	
	public Double getConsume() {
		return consume;
	}
	public void setConsume(Double consume) {
		this.consume = consume;
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
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	public Integer getCommentId() {
		return commentId;
	}
	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Double getRate1() {
		return rate1;
	}
	public void setRate1(Double rate1) {
		this.rate1 = rate1;
	}
	public double getRate2() {
		return rate2;
	}
	public void setRate2(Double rate2) {
		this.rate2 = rate2;
	}
	public Double getRate3() {
		return rate3;
	}
	public void setRate3(Double rate3) {
		this.rate3 = rate3;
	}
	
}
