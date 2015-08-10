package com.t.core.entities;

import java.sql.Timestamp;

/**
 * 校园新闻动态
 * @author WangZhaoLi
 *
 */
public class SchoolNews {
     private int topicId;
     private String title;
     private String content;
     private int schoolId;
     private Timestamp date;
     private  int topicType;
     
	public int getTopicId() {
		return topicId;
	}
	public void setTopicId(int topicId) {
		this.topicId = topicId;
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
	public int getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(int schoolId) {
		this.schoolId = schoolId;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public int getTopicType() {
		return topicType;
	}
	public void setTopicType(int topicType) {
		this.topicType = topicType;
	}

     
	
     
}
