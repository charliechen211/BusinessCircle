package com.t.core.entities;

 

public class ShGood {
	
	private Integer id;
	private Integer userId;
	private Integer shId;
	private Integer Good;
	private Integer type;
	
	public ShGood() {}

	public ShGood(Integer id, Integer userId, Integer shId, Integer good, Integer type) {
		this.id = id;
		this.userId = userId;
		this.shId = shId;
		this.Good = good;
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

	public Integer getShId() {
		return shId;
	}

	public void setShId(Integer shId) {
		this.shId = shId;
	}

	public Integer getGood() {
		return Good;
	}

	public void setGood(Integer good) {
		this.Good = good;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
}
