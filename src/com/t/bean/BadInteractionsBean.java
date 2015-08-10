package com.t.bean;
/**
 * 被举报的帖子实体，返回给前台进行审核
 * @author WangZhaoLi
 *
 */
public class BadInteractionsBean {
	private int topicId;
    private String userName;
    private String content;
    private int alarmNum;
    private String time;
       
    
	public int getTopicId() {
		return topicId;
	}
	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getAlarmNum() {
		return alarmNum;
	}
	public void setAlarmNum(int alarmNum) {
		this.alarmNum = alarmNum;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
    
    
    
}
