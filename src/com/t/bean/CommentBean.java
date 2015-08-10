package com.t.bean;

import java.sql.Timestamp;

import com.t.core.entities.Comment;
//本类用于用户评论内容的显示
public class CommentBean {
	private Integer commentId;
	private String userName;
	private Integer entityId;
	private Integer entityType;
	private Integer userId;
	private String content;
	private Double rate1;
	private Double rate2;
	private Double rate3;
	private Double consume;
	private Timestamp timestamp;
	public CommentBean(){}
	public CommentBean(Comment comment){
		this.commentId = comment.getCommentId();
		this.entityId = comment.getEntityId();
		this.entityType = comment.getEntityType();
		this.userId = comment.getUserId();
		this.content = comment.getContent();
		this.rate1 = comment.getRate1();
		this.rate2 = comment.getRate2();
		this.rate3 = comment.getRate3();
		this.timestamp = comment.getTimestamp();
		this.consume = comment.getConsume();
	}
	public Integer getCommentId() {
		return commentId;
	}
	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
	public Double getRate2() {
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
	public Double getConsume() {
		return consume;
	}
	public void setConsume(Double consume) {
		this.consume = consume;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

}
