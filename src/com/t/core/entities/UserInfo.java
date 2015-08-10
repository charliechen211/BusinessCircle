package com.t.core.entities;

public class UserInfo {

	private Integer userId;
	private String nickname;
	private Integer age;
	private Integer job;
	private Integer hometown;
	private Integer income;
	private Integer point;
	private Integer sex;
	private String picture;
	
	private Integer schoolId;
	private Integer regionId;
	
	public UserInfo(){}
	public UserInfo(Integer userId, String nickname, Integer age, Integer job,
			Integer hometown, Integer income, Integer point, Integer sex,String picture) {
		this.userId = userId;
		this.nickname = nickname;
		this.age = age;
		this.job = job;
		this.hometown = hometown;
		this.income = income;
		this.point = point;
		this.sex = sex;
		this.picture = picture;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Integer getJob() {
		return job;
	}
	public void setJob(Integer job) {
		this.job = job;
	}
	public Integer getHometown() {
		return hometown;
	}
	public void setHometown(Integer hometown) {
		this.hometown = hometown;
	}
	public Integer getIncome() {
		return income;
	}
	public void setIncome(Integer income) {
		this.income = income;
	}
	public Integer getPoint() {
		return point;
	}
	public void setPoint(Integer point) {
		this.point = point;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	public Integer getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}
	public Integer getRegionId() {
		return regionId;
	}
	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}
	
}
