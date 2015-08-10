package com.t.core.entities;
/**
 * 二手和鹊桥的帖子实体
 * @author WangZhaoLi
 *
 */
import java.sql.Timestamp;

public class ShLVInfo {
	private Integer shId;
	private Integer userId;
	private Integer circleId;
	private String title;
	private String description;
	private Timestamp timestamp;
	private Integer type;
	private Integer delFlg;
	
	private String linkWay;
	private String picture;
	private int commentNum;
	
	public ShLVInfo() {}
	
	public ShLVInfo(Integer shId, Integer userId, Integer circleId, String title,
			String description, Timestamp timestamp, Integer type, Integer delFlg,String linkWay,String picture) {
		this.shId = shId;
		this.userId = userId;
		this.circleId = circleId;
		this.title = title;
		this.description = description;
		this.timestamp = timestamp;
		this.type = type;
		this.delFlg = delFlg;
		this.linkWay = linkWay;
		this.picture = picture;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getShId() {
		return shId;
	}

	public void setShId(Integer shId) {
		this.shId = shId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getCircleId() {
		return circleId;
	}

	public void setCircleId(Integer circleId) {
		this.circleId = circleId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getLinkWay() {
		return linkWay;
	}

	public void setLinkWay(String linkWay) {
		this.linkWay = linkWay;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public int getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}	
	
	
}
