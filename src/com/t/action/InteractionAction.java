package com.t.action;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.t.bean.BadInteractionsBean;
import com.t.bean.InteractionBean;
import com.t.core.entities.Interaction;
import com.t.service.interfaces.IInteractionService;
import com.t.utils.BaseAction;

public class InteractionAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private int topicId;
	private int userId; //发帖者id
	private String content;
	private Timestamp date;
	private int isValid;
	private int alarmNum;
	private int replyNum;
	private int schoolId;

	private Integer pageId;
	private Integer pageSize;

	@Autowired
	private IInteractionService InterServ;



	public String addInteraction(){  //添加
		Interaction ele = new Interaction();
		try {
			//content = new String(content.getBytes("ISO-8859-1"), "UTF-8");
			//System.out.println("**********************************"+content);
			ele.setContent(content);
			ele.setSchoolId(schoolId);
			ele.setUserId(userId);
			InterServ.addInteraction(ele);
			result.put(STATE,SUCCESS);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			result.put(STATE,FAIL);
			return FAIL;
		}
	}

	public String delInteraction(){  //删除
		try{
		InterServ.delInteractionById(topicId);
		result.put(STATE,SUCCESS);
		return SUCCESS;
		}catch (Exception e) {
			e.printStackTrace();
			result.put(STATE,FAIL);
			return FAIL;
		}
	}

	public String reportBad(){  //举报
		try{
			InterServ.reportBad(topicId);
			result.put(STATE,SUCCESS);
			return SUCCESS;
		} catch (Exception e) {
			result.put(STATE,FAIL);
			return FAIL;
		}
	}

	public void passInteraction(){
		InterServ.passInteraction(topicId);
	}

	public String getList(){  //获得集合
		List<InteractionBean> list = new ArrayList<InteractionBean>();
		try{
		list = InterServ.getList(pageId, pageSize,schoolId,userId);
		result.put("result",list);
		result.put(STATE,SUCCESS);
		return SUCCESS;
		}catch (Exception e) {
			result.put(STATE,FAIL);
			return FAIL;
		}
	}

	public String getBadNews(){  //web端获得被举报帖子的集合
		List<BadInteractionsBean> badnewslist = new ArrayList<BadInteractionsBean>();
		badnewslist = InterServ.getBadNews();

		if(badnewslist != null){
			ActionContext.getContext().put("badList", badnewslist);
			return "badlist";
		}
		return "empty";
	}


	public int getTopicId() {
		return topicId;
	}

	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}

	public Integer getPageId() {
		return pageId;
	}
	public void setPageId(Integer pageId) {
		this.pageId = pageId;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
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
	public int getIsValid() {
		return isValid;
	}
	public void setIsValid(int isValid) {
		this.isValid = isValid;
	}
	public int getAlarmNum() {
		return alarmNum;
	}
	public void setAlarmNum(int alarmNum) {
		this.alarmNum = alarmNum;
	}
	public int getReplyNum() {
		return replyNum;
	}
	public void setReplyNum(int replyNum) {
		this.replyNum = replyNum;
	}

	public int getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(int schoolId) {
		this.schoolId = schoolId;
	}



}
