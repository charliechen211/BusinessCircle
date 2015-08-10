package com.t.action;

import java.sql.Time;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.t.core.entities.MerchantTableInfo;
import com.t.service.impl.MerchantTableManagerService;
import com.t.service.interfaces.IMerchantTableManagerService;
import com.t.utils.BaseAction;

public class MerchantTableManagerAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	private Integer mtiId;
	private Integer merchantId;
	private String tableType;
	private String description;
	private String startTime;
	private String endTime;

	private int tempId; 
	
	private List<MerchantTableInfo> tablelist;
	private Boolean booleanResult;
	private Map<String, List<MerchantTableInfo>> findResult;

	@Autowired
	private IMerchantTableManagerService service;

	public String findByMerchantId(){
		try{
			List<MerchantTableInfo> result = service.findByMerchantId(merchantId);
			findResult = new HashMap<String, List<MerchantTableInfo>>();
			findResult.put("result", result);
			return SUCCESS;
		}catch(Exception e){
			return FAIL;
		}
	}

	//添加
	public String addMerchantTableInfo(){
		MerchantTableInfo table = new MerchantTableInfo(merchantId, tableType, description, startTime, endTime);

		booleanResult = service.addMerchantTableInfo(table);
		if(booleanResult)
		{			
			ActionContext.getContext().put("table",table);
			return SUCCESS;
		}else
			return FAIL;
	}

	public String removeMerchantTableInfo(){
		MerchantTableInfo table = service.findTableById(mtiId);
		booleanResult = service.removeMerchantTableInfo(table);	
		tablelist = service.findByMerchantId(merchantId);
		ActionContext.getContext().put("tablelist",tablelist);
		if(booleanResult)
		{			
			return SUCCESS;
		}else
			return FAIL;
	}


	public String tableFindById(){
		MerchantTableInfo table = service.findTableById(mtiId);
		try {
			ActionContext.getContext().put("table",table);
			return SUCCESS;
		}	catch (Exception e){
			return FAIL;
		}
	}

	/*修改*/
	public String modifyMerchantTableInfo(){
		MerchantTableInfo table = service.findTableById(mtiId);
		table.setTableType(tableType);
		table.setDescription(description);
		table.setStartTime(startTime);
		table.setEndTime(endTime);

		booleanResult = service.modifyMerchantTableInfo(table);
		if(booleanResult)
		{			
			ActionContext.getContext().put("table",table);
			return SUCCESS;
		}else
			return FAIL;
	}

	/*获得商铺列表*/
	@SuppressWarnings("unchecked")
	public String MerchantList(){
		
		if(this.getMyshopids().size()!=0){			
			ActionContext.getContext().put("merchantlist",this.getMymerchantlist());
			ActionContext.getContext().put("merchantId", merchantId);
			return SUCCESS;
		}
		else 
			return FAIL;
	}

	/*根据商铺id获得桌位列表*/
	public String getTableList(){
		try{
			tablelist = service.findByMerchantId(merchantId);
			ActionContext.getContext().put("tablelist",tablelist);
			return SUCCESS;
		}catch (Exception e){
			return FAIL;
		}
	}


	public Map<String, List<MerchantTableInfo>> getFindResult() {
		return findResult;
	}

	public void setFindResult(Map<String, List<MerchantTableInfo>> findResult) {
		this.findResult = findResult;
	}

	public Boolean getBooleanResult() {
		return booleanResult;
	}
	public void setBooleanResult(Boolean result) {
		this.booleanResult = result;
	}
	public Integer getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}
	public String getTableType() {
		return tableType;
	}
	public void setTableType(String tableType) {
		this.tableType = tableType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public Integer getMtiId() {
		return mtiId;
	}

	public void setMtiId(Integer mtiId) {
		this.mtiId = mtiId;
	}

	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}


}
