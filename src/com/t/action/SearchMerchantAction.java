package com.t.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.t.bean.MerchantBean;
import com.t.bean.UserBean;
import com.t.service.interfaces.ISearchMerchantService;
import com.t.utils.BaseAction;

public class SearchMerchantAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	private String content;
	private Integer pageId;
	private Integer pageSize;
	private Double longitude;
	private Double latitude;
	private Double distance;
	
	private Integer circleId;
	private Integer typeId;
	//搜索用户
	private List<UserBean> userBeans;
	
	@Autowired
	private ISearchMerchantService searchMerchantServe;
	//根据content搜索相应的商户，结果分页显示
	public String searchMerchant(){
		String s = content;
		System.out.print(s);
		results = searchMerchantServe.searchMerchant(content,pageId,pageSize,circleId,typeId);
		result.put("results", results);
		result.put(STATE,SUCCESS);
		
		return SUCCESS;
	}
	//基于地理位置对商户进行显示
	public String searchBasedLocation(){
		results = searchMerchantServe.searchBasedLocation(longitude, latitude,distance,pageId,pageSize);
		result.put("results", results);			
		result.put(STATE, SUCCESS);
	
		return SUCCESS;
	}
	//搜索用户
	public String searchUser(){
		userBeans = searchMerchantServe.searchUser(content, pageId, pageSize);
		result.put("results", userBeans);
		result.put(STATE, SUCCESS);
	
		return SUCCESS;
	}
	
	public Integer getCircleId() {
		return circleId;
	}
	public void setCircleId(Integer circleId) {
		this.circleId = circleId;
	}
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	public List<UserBean> getUserBeans() {
		return userBeans;
	}
	public void setUserBeans(List<UserBean> userBeans) {
		this.userBeans = userBeans;
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
	private List<MerchantBean> results;
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public List<MerchantBean> getResults() {
		return results;
	}
	public void setResults(List<MerchantBean> results) {
		this.results = results;
	}
	
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getDistance() {
		return distance;
	}
	public void setDistance(Double distance) {
		this.distance = distance;
	}

}
