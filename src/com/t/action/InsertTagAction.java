package com.t.action;

import java.util.List;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;

import com.t.service.interfaces.ITagService;
import com.t.utils.BaseAction;

public class InsertTagAction extends BaseAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer userId;
	
	private Integer entityId;
	
	private Long entityType;
	
	private List<String> content;
	private Boolean insertResult;
	
	@Autowired
	private ITagService service;

	public String insert(){
		
		try {
			insertResult = service.insertTag(userId, entityId, entityType, content);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return FAIL;
		}
		return SUCCESS;
	}
	
	public void setUserId(int userId){
		this.userId = userId;
	}
	
	public int getUesrId(){
		return this.userId;
	}
	
	public void setEntityId(int entityId){
		this.entityId = entityId;
	}
	
	public int getEntityId(){
		return this.entityId;
	}
	
	public void setEntityType(long entityType){
		this.entityType = entityType;
	}
	
	public long getEntityType(){
		return this.entityType;
	}
	
	public List<String> getContent(){
		return this.content;
	}
	
	public void setContent(List<String> content){
		this.content = content;
	}
	
	public Boolean getInsertResult(){
		return this.insertResult;
	}
	
	public void setInsertResult(Boolean insertResult){
		this.insertResult = insertResult;
	}
}
