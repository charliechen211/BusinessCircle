package com.t.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;

import com.t.bean.ModuleObjectBean;
import com.t.bean.RecommendFriendBean;
import com.t.bean.SchoolsRegionsBean;
import com.t.bean.UserBean;
import com.t.service.interfaces.ITagService;
import com.t.service.interfaces.IUserService;
import com.t.utils.BaseAction;

public class UserAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//注册时必填的项
	private String mobilePhone;
	private String password;
	private Integer sex;
	private Integer age;
	//注册时可选的项
	private String tags;
	private String nickname;
	private Integer job;
	private Integer hometown;
	private Integer income;
	private String weibo;
	private Integer point;
	private Integer loginResult;
	private String picture;

	//用于拉取用户的详细信息、用户的好友信息
	private Integer userId;
	private UserBean userBean;
	private List<UserBean> friends;

	private Integer tagId;
	
	private Integer score;
	
	private String tagContent;

	private List<UserBean> fans;

	private List<String> tagLibs;

	private List<String> myTags;

	private List<SchoolsRegionsBean> srlist;

	private List<ModuleObjectBean> recommendlist;

	private Integer schoolId;
	private Integer regionId;

	//用于添加关注、解除关注
	private Integer friendId;
	//获取好友推荐
	private List<RecommendFriendBean> recommendFriends;
	@Autowired
	private IUserService userServe;
	@Autowired
	private ITagService  tagServe;

	//用户注册--我的大学
	public String register(){

		Integer userId;
		try {
			userId = userServe.register(mobilePhone,password,sex,age,   //必选项
					nickname,/*job,hometown,income,point*/schoolId,regionId,picture,tagContent);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.put(STATE,FAIL);
			return FAIL;
		}
		List<String> tagList = new ArrayList<String>();
		//插入用户注册时的标签
		if (tags != null && userId != -1){
			String tag[]  = tags.split(";");
			tagList = Arrays.asList(tag);
			// TODO insert tag in register
//			tagServe.insertTag(userId, userId, new Long(0), tagList);
		}
		if(userId == -1)
		{
			result.put(STATE,FAIL);
		}
		else{
			result.put("userId",userId);
			result.put(STATE, SUCCESS);
		}
		return SUCCESS;
	}

	//用户注册--智慧商圈
	public String bcregister(){
		Integer userId = userServe.bcregister(mobilePhone,password,sex,age,   //必选项
				nickname,job,hometown,income,point,picture);
		List<String> tagList = new ArrayList<String>();
		//插入用户注册时的标签
		if (tags != null && userId != -1){
			String tag[]  = tags.split(";");
			tagList = Arrays.asList(tag);
			// TODO insert tag in register
//			tagServe.insertTag(userId, userId, new Long(0), tagList);
		}
		if(userId == -1)
		{
			result.put(STATE,FAIL);
		}
		else{
			result.put("userId",userId);
			result.put(STATE, SUCCESS);
		}
		return SUCCESS;
	}

	//用户登录
	public String login(){

		loginResult = userServe.login(mobilePhone, password);
		if(loginResult == -1){
			result.put("result","-1");
			result.put(STATE, FAIL);
			return FAIL;
		}
		else {
			userBean = userServe.fetchUserBean(loginResult);
			if(userBean.getUserId() == null){
				result.put(STATE, FAIL);
			}else{
				result.put("result", userBean);
				result.put(STATE, SUCCESS);
			}
			return SUCCESS;
		}
	}

	//用于拉取用户的详细信息、用户的好友信息
	public String fetchUserBean(){
		userBean = userServe.fetchUserBean(userId);
		if(userBean.getUserId() == null){
			result.put(STATE, FAIL);
		}else{
			result.put("result", userBean);
			result.put(STATE, SUCCESS);
		}
		return SUCCESS;
	}

	//拉取用户的好友信息
	public String fetchFriends(){
		friends = userServe.fetchFriends(userId);
		result.put("results", friends);
		result.put(STATE, SUCCESS);
		return SUCCESS;
	} 
	//用于添加关注、解除关注
	public String addFriend(){
		if(userServe.addFriend(userId, friendId) == -1){
			result.put(STATE, FAIL);
		}else{
			result.put(STATE, SUCCESS);
		}
		return SUCCESS;
	}

	public String deleteFriend(){
		if(userServe.deleteFriend(userId, friendId) == -1){
			result.put(STATE, FAIL);
		}else{
			result.put(STATE, SUCCESS);
		}
		return SUCCESS;
	}

	//获取关注我的人，即我的粉丝
	public String getMyFans(){
		fans = userServe.getMyFans(userId);		
		result.put("results", fans);
		result.put(STATE, SUCCESS);
		return SUCCESS;
	}

	//获取好友推荐
	public String getRecommendFriendBeans(){
		recommendFriends = userServe.getRecommendFriends(userId);
		result.put("results", recommendFriends);
		result.put(STATE, SUCCESS);
		return SUCCESS;
	}

	//获取标签库
	public String getTagLib(){
		tagLibs = userServe.getTagLib();
		result.put("results", tagLibs);
		result.put(STATE, SUCCESS);
		return SUCCESS;
	}

	//获取用户自定义标签
	public String getMyTags(){
		myTags = userServe.getMyTags(userId);
		result.put("results", myTags);
		result.put(STATE, SUCCESS);
		return SUCCESS;
	}

	//添加用户自定义标签
	public String addTag(){
		try {
			userServe.addTag(userId,tagContent);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.put(STATE, FAIL);
			return FAIL;
		}
		result.put(STATE, SUCCESS);
		return SUCCESS;
	}

	//获得学校和校区列表
	public String getSchoolsRegions(){
		srlist = userServe.getAllSchoolsAndRegions();
		result.put("results", srlist);
		result.put(STATE, SUCCESS);
		return SUCCESS;
	}

	//修改个人信息
	public String modifyInfo(){
		try{
			userServe.modifyInfo(userId, picture, nickname, schoolId, regionId, tagContent);
			result.put(STATE, SUCCESS);
			return SUCCESS;
		}catch (Exception e){
			e.printStackTrace();
			result.put(STATE, FAIL);
			return FAIL;
		}
	}
	
	//更新个人积分
	public String updateScore (){
		try{
			userServe.updateScore(userId, score);
			result.put(STATE, SUCCESS);
			return SUCCESS;
		}catch (Exception e){
			e.printStackTrace();
			result.put(STATE, FAIL);
			return FAIL;
		}
	}


	//获得推荐列表
	public String recommendInfos(){
		try{
			recommendlist = userServe.recommendInfos();
			result.put("results", recommendlist);
			result.put(STATE, SUCCESS);
			return SUCCESS;
		}catch (Exception e){
			e.printStackTrace();
			result.put(STATE, FAIL);
			return FAIL;
		}
	}

	public Integer getLoginResult() {
		return loginResult;
	}
	public void setLoginResult(Integer loginResult) {
		this.loginResult = loginResult;
	}
	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

	public String getWeibo() {
		return weibo;
	}

	public void setWeibo(String weibo) {
		this.weibo = weibo;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
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
	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public UserBean getUserBean() {
		return userBean;
	}
	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}
	public List<UserBean> getfriends() {
		return friends;
	}
	public void setfriends(List<UserBean> friends) {
		this.friends = friends;
	}
	public List<UserBean> getFriends() {
		return friends;
	}
	public void setFriends(List<UserBean> friends) {
		this.friends = friends;
	}
	public Integer getFriendId() {
		return friendId;
	}
	public void setFriendId(Integer friendId) {
		this.friendId = friendId;
	}
	public void setRecommendFriends(List<RecommendFriendBean> recommendFriends) {
		this.recommendFriends = recommendFriends;
	}
	public List<RecommendFriendBean> getRecommendFriends() {
		return recommendFriends;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Integer getTagId() {
		return tagId;
	}

	public void setTagId(Integer tagId) {
		this.tagId = tagId;
	}

	public String getTagContent() {
		return tagContent;
	}

	public void setTagContent(String tagContent) {
		this.tagContent = tagContent;
	}

	public List<String> getTagLibs() {
		return tagLibs;
	}

	public void setTagLibs(List<String> tagLibs) {
		this.tagLibs = tagLibs;
	}

	public void setMyTags(List<String> myTags) {
		this.myTags = myTags;
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

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}



}
