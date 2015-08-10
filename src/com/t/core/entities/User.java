package com.t.core.entities;

import java.sql.Timestamp;

public class User implements java.io.Serializable {

	private Integer userId;
	private String password;
	private String userName;
	private Timestamp regDate;
	private String mobilePhone;
	private String weiboAccount;
	
	public User(){
		
	}
	public User(Integer userId, String password, 
			String userName, String mobilePhone, String weiboAccount){
		this.userId = userId;
		this.password = password;
		this.userName = userName;
		this.regDate = null;
		this.mobilePhone = mobilePhone;
		this.weiboAccount = weiboAccount;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Timestamp getRegDate() {
		return regDate;
	}

	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getWeiboAccount() {
		return weiboAccount;
	}

	public void setWeiboAccount(String weiboAccount) {
		this.weiboAccount = weiboAccount;
	}
	

}
