package com.t.bean;

import com.t.core.entities.ShLVInfo;


public class ShInfoBean {

	private ShLVInfo shlvinfo;
	private String timestamp;	
	private String userName;
	private String userPic;
	
	public ShInfoBean() {}
	 
	public ShLVInfo getShlvinfo() {
		return shlvinfo;
	}

	public void setShlvinfo(ShLVInfo shlvinfo) {
		this.shlvinfo = shlvinfo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPic() {
		return userPic;
	}

	public void setUserPic(String userPic) {
		this.userPic = userPic;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

}
