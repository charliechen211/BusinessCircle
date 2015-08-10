package com.t.bean;

import java.util.List;

public class RecommendFriendBean {
	private Integer userId;
	private String userName;
	private String picture;
	private List<String> tags;
	private Integer point;
	private Integer fanNum;
	private Integer followNum;
	private String intermediary;   //中间人

	public RecommendFriendBean(){
	}
	public RecommendFriendBean(UserBean bean){
		this.userId = bean.getUserId();
		this.userName = bean.getUserName();
		this.picture = bean.getPicture();
		this.tags = bean.getTags();
		this.point = bean.getPoint();
		this.fanNum = bean.getFanNum();
		this.followNum = bean.getFollowNum();
	}
	
	public String getIntermediary() {
		return intermediary;
	}

	public void setIntermediary(String intermediary) {
		this.intermediary = intermediary;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	public Integer getPoint() {
		return point;
	}
	public void setPoint(Integer point) {
		this.point = point;
	}
	public Integer getFanNum() {
		return fanNum;
	}
	public void setFanNum(Integer fanNum) {
		this.fanNum = fanNum;
	}
	public Integer getFollowNum() {
		return followNum;
	}
	public void setFollowNum(Integer followNum) {
		this.followNum = followNum;
	}
	
	

}
