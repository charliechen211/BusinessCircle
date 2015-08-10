package com.t.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.t.bean.SchoolNewsBean;
import com.t.service.interfaces.ISchoolnewsService;
import com.t.utils.BaseAction;


public class SchoolNewsAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	private int topicId;
	private int topicType;
	private int schoolId;
	private Integer pageId;
	private Integer pageSize;

	@Autowired
	public  ISchoolnewsService snServ ;


	public String getList(){
		List<SchoolNewsBean> list = new ArrayList<SchoolNewsBean>();
		try{
			list = snServ.getList(topicType,schoolId,pageId,pageSize);
			result.put("result",list);
			result.put(STATE,SUCCESS);
			return SUCCESS;
		}catch (Exception e){
			result.put(STATE,FAIL);
			return FAIL;
		}
	}

	public String getContent(){   
		try{
			result.put("result",snServ.getContent(topicId) );
			result.put(STATE,SUCCESS);
			return SUCCESS;
		}catch (Exception e){
			result.put(STATE,FAIL);
			return FAIL;
		}
	}

	public int getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(int schoolId) {
		this.schoolId = schoolId;
	}

	public int getTopicId() {
		return topicId;
	}
	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}
 
	public int getTopicType() {
		return topicType;
	}

	public void setTopicType(int topicType) {
		this.topicType = topicType;
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
	

}
