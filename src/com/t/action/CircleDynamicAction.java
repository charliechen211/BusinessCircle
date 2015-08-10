package com.t.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.t.bean.CircleDynamicBean;
import com.t.bean.FriendDynamicBean;
import com.t.service.interfaces.ICircleDynamicService;
import com.t.service.interfaces.IFriendDynamicService;
import com.t.utils.BaseAction;

public class CircleDynamicAction extends BaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer userId;
	private Integer entityId;
	private Integer typeId;
	private String content;
	private Integer circleId;
	private Integer shareTo;
	private Integer shareId;
	private List<CircleDynamicBean> results;
	private List<FriendDynamicBean> friendDynamicBeans;
	@Autowired
	private ICircleDynamicService circleDynamicServe;
	@Autowired
	private IFriendDynamicService friendDynamicService;
	
	
	public Integer getShareId() {
		return shareId;
	}
	public void setShareId(Integer shareId) {
		this.shareId = shareId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getEntityId() {
		return entityId;
	}
	public void setEntityId(Integer entityId) {
		this.entityId = entityId;
	}
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	public Integer getShareTo() {
		return shareTo;
	}
	public void setShareTo(Integer shareTo) {
		this.shareTo = shareTo;
	}
	public List<CircleDynamicBean> getResults() {
		return results;
	}
	public void setResults(List<CircleDynamicBean> results) {
		this.results = results;
	}
	public Integer getCircleId() {
		return circleId;
	}
	public void setCircleId(Integer circleId) {
		this.circleId = circleId;
	}
	
	public List<FriendDynamicBean> getFriendDynamicBeans() {
		return friendDynamicBeans;
	}
	public void setFriendDynamicBeans(List<FriendDynamicBean> friendDynamicBeans) {
		this.friendDynamicBeans = friendDynamicBeans;
	}
	//添加到商圈动态
	public String addCircleDynamic(){
		Integer r = circleDynamicServe.addCircleDynamic(userId,entityId,typeId,content,shareTo);
		if(r == -1)
			result.put(STATE,FAIL);
		else {
			result.put(STATE,SUCCESS);
		}
		return SUCCESS;
	}
	//拉取商圈id为circleId的商圈的动态
	public String fetchCircleDynamic(){
		results = circleDynamicServe.fetchCircleDynamic(circleId);
		if(results.size() == 0){
			result.put(STATE, FAIL);
		}else{
			result.put("results", results);
			result.put(STATE, SUCCESS);
		}
		return SUCCESS;
	}
	//拉取好友的动态
	public String fetchFriendDynamic(){
		/*friendDynamicBeans = friendDynamicService.fetchFriendDynamic(userId);
		result.put("results", friendDynamicBeans);
		result.put(STATE, SUCCESS);
		return SUCCESS;*/
		results = friendDynamicService.fetchFriendDynamic(userId);
		if(results.size() == 0){
			result.put(STATE, FAIL);
		}else{
			result.put("results", results);
			result.put(STATE, SUCCESS);
		}
		return SUCCESS;
	}
	
	//拉取用户id为userId的用户分享
	public String fetchMysharing(){
		results = circleDynamicServe.fetchMySharing(userId);
		result.put("results", results);
		result.put(STATE, SUCCESS);
		return SUCCESS;
	}
	
	//删除用户分享
	public String delMySharing() {
		result.put(STATE, circleDynamicServe.delMySharing(shareId));
		return SUCCESS;
	}

}
