package com.t.bean;

 
import java.util.ArrayList;
import java.util.List;
/*
 * By liangyunlong
 */
public class UserBean {
	private Integer userId;
	private String userName;
	private String picture;
	private List<String> tags;
	
	private List<String> usertags;
	
	private Integer point;
	private Integer fanNum;
	private Integer followNum;
	private Integer home;
	private Integer job;
	private Integer age;

    private String schoolName;
    private String regionName;
    
    private int userScore;
    
	public UserBean(){
		userId = 0;
		userName = "";
		picture = "";
		tags = new ArrayList<String>();
		usertags = new ArrayList<String>();
		point = 0;
		fanNum = 0;
		followNum = 0;
		home = 0;
		job = 0;
		age = 0;
		schoolName = "";
		regionName = "";
		userScore = 0;		
	}

	public UserBean(Integer userId, String userName, String picture,
			List<String> tags, Integer point, Integer fanNum, Integer followNum) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.picture = picture;
		this.tags = tags;
		this.point = point;
		this.fanNum = fanNum;
		this.followNum = followNum;
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
	public Integer getHome() {
		return home;
	}
	public void setHome(Integer home) {
		this.home = home;
	}
	public Integer getJob() {
		return job;
	}
	public void setJob(Integer job) {
		this.job = job;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	public List<String> getUsertags() {
		return usertags;
	}
	public void setUsertags(List<String> usertags) {
		this.usertags = usertags;
	}

	public int getUserScore() {
		return userScore;
	}

	public void setUserScore(int userScore) {
		this.userScore = userScore;
	}
	
	

}
