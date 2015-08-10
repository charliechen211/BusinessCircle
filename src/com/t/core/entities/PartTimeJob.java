package com.t.core.entities;

import java.sql.Timestamp;

/**
 * 兼职信息
 * @author WangZhaoLi
 *
 */
public class PartTimeJob {
    private Integer jobId;
    private int userId;
    private String title;
    private String content;  
    private int jobtype;
    private String jobname;
    private String place;
    private String pay;
    private String requirement;
    private String linkway;  
    private int commentNum;
    private Timestamp date;
      
	public Integer getJobId() {
		return jobId;
	}
	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public int getJobtype() {
		return jobtype;
	}
	public void setJobtype(int jobtype) {
		this.jobtype = jobtype;
	}
	public String getJobname() {
		return jobname;
	}
	public void setJobname(String jobname) {
		this.jobname = jobname;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	 
	public String getRequirement() {
		return requirement;
	}
	public void setRequirement(String requirement) {
		this.requirement = requirement;
	}
	public String getLinkway() {
		return linkway;
	}
	public void setLinkway(String linkway) {
		this.linkway = linkway;
	}
	public String getPay() {
		return pay;
	}
	public void setPay(String pay) {
		this.pay = pay;
	}
	public int getCommentNum() {
		return commentNum;
	}
	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}
    
    
}
