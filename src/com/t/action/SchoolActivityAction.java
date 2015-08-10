package com.t.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.t.bean.SchoolActivityBean;
import com.t.service.interfaces.ISchoolActivityService;
import com.t.utils.BaseAction;

public class SchoolActivityAction extends BaseAction {
	
	private static final long serialVersionUID = 1L;
	
	private int schactId;
	private Integer pageId;
	private Integer pageSize;
	
	//发布活动必填项
	private String title;
	private String content;
	private String access;
	private int userId;

	//发布活动可选项
	private String picture;
	
	@Autowired
	public  ISchoolActivityService schactSev;
	
	//获得校园活动列表
	public String getList(){
		List<SchoolActivityBean> list = new ArrayList<SchoolActivityBean>();
		try{
			list = schactSev.getList(pageId,pageSize);
			result.put("result",list);
			result.put(STATE,SUCCESS);
			return SUCCESS;
		}catch (Exception e){
			result.put(STATE,FAIL);
			return FAIL;
		}
	}
	
	//获得活动详情
	public String getActivity(){   
		try{
			result.put("result",schactSev.getActivity(schactId) );
			result.put(STATE,SUCCESS);
			return SUCCESS;
		}
		catch (Exception e){
			result.put(STATE,FAIL);
			return FAIL;
		}
	}
	
	//校园活动发布
		public String publish(){	
			try{
				schactSev.activitypublish(title,content,access,picture,userId);
				result.put(STATE,SUCCESS);
				return SUCCESS;
			}
			catch (Exception e){
				result.put(STATE,FAIL);
				return FAIL;
			}
		}

	public int getSchactId() {
		return schactId;
	}

	public void setSchactId(int schactId) {
		this.schactId = schactId;
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

	public String getAccess() {
		return access;
	}

	public void setAccess(String access) {
		this.access = access;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

}
