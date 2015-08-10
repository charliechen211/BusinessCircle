package com.t.core.entities;

public class UserFriend {
	private Integer ufId;
	private Integer userId;
	private Integer friendId;
	private Double weight;
	public UserFriend(){}
	public UserFriend(Integer ufId, Integer userId, Integer friendId,
			Double weight) {
		this.ufId = ufId;
		this.userId = userId;
		this.friendId = friendId;
		this.weight = weight;
	}
	public Integer getUfId() {
		return ufId;
	}
	public void setUfId(Integer ufId) {
		this.ufId = ufId;
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
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	
	
}
