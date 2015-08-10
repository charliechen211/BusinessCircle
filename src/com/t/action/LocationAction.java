package com.t.action;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;

import com.t.service.interfaces.ILocationService;
import com.t.utils.BaseAction;


public class LocationAction extends BaseAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private String select;
	private Integer pageId;
	private Integer pageSize;
	
	
	@Autowired
	private ILocationService locationService;
	
	public String fetchLocations(){
		
		try{
	//		select = new String(select.getBytes("UTF-8"), "UTF-8");
			result.put("results", locationService.fetchLocations(select,pageId,pageSize));
			result.put(STATE,SUCCESS);
			return SUCCESS;
		}catch(Exception e){
			result.put(STATE, FAIL);
			return SUCCESS;
		}
	}

	public String getSelect() {
		return select;
	}

	public void setSelect(String select) {
		this.select = select;
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
