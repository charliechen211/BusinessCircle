package com.t.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.t.bean.MerchantQueueBean;
import com.t.bean.QueueBean;
import com.t.bean.TableOrderBean;
import com.t.core.entities.Merchant;
import com.t.core.entities.MerchantTableInfo;
import com.t.core.entities.Queue;
import com.t.service.interfaces.IMerchantService;
import com.t.service.interfaces.IMerchantTableManagerService;
import com.t.service.interfaces.IQueueService;
import com.t.utils.BaseAction;

public class QueueAction extends BaseAction{

	private Integer merchantId;
	private Integer userId;
	private String tableType;
	private String orderNum;
	private String status;
	private String startTime;
	private String endTime;

	private Integer count;
	private List<Integer> queueToNotice;

	private List<Queue> queueResult;
	private Boolean booleanResult;
	private Integer orderResult;
	private QueueBean queueBean;
	private Map<String, Object> queueMap;

	@Autowired
	private IMerchantService mService;
	
	@Autowired
	private IQueueService service;

	@Autowired
	private IMerchantTableManagerService merchantService;


	@SuppressWarnings("unchecked")
	public String getUserQueue(){
		queueMap = new HashMap<String, Object>();

		Set<Integer> merchantSet = service.getMerchantIdByUser(userId);

		List<MerchantQueueBean> list = new ArrayList<MerchantQueueBean>();
		Iterator iter = merchantSet.iterator();
		while(iter.hasNext()){
			int merchantId = (Integer)iter.next();
			List<Queue> queueList = service.getMerchantQueue(userId, merchantId);
			List<MerchantTableInfo> tableList = merchantService.findByMerchantId(merchantId);
			List<TableOrderBean> tableOrderBeanList = new ArrayList<TableOrderBean>();
			for(int j=0; j<tableList.size(); j++){
				Queue tmp = service.getCurrentOrder(merchantId, tableList.get(j).getTableType());
				if(tmp!=null)
					tableOrderBeanList.add(new TableOrderBean(tableList.get(j).getTableType(), tmp.getOrderNum()));
				else
					tableOrderBeanList.add(new TableOrderBean(tableList.get(j).getTableType(), 0));
			}
			Merchant merchant = mService.shopFindByShopId(String.valueOf(merchantId));
			list.add(new MerchantQueueBean(merchantId, merchant.getMerchantName(), merchant.getPicture(), queueList, tableOrderBeanList));
		}

		queueMap.put("result", list);
		return SUCCESS;
	}

	//返回给手机端
	public String getMerchantQueue(){
		queueMap = new HashMap<String, Object>();
		queueMap.put("result", service.getMerchantQueue(userId, merchantId));

		List<MerchantTableInfo> tableList = merchantService.findByMerchantId(merchantId);
		List<TableOrderBean> list = new ArrayList<TableOrderBean>();
		for(int i=0; i<tableList.size(); i++){
			Queue tmp = service.getCurrentOrder(merchantId, tableList.get(i).getTableType());
			if(tmp!=null)
				list.add(new TableOrderBean(tableList.get(i).getTableType(), tmp.getOrderNum()));
			else
				list.add(new TableOrderBean(tableList.get(i).getTableType(), 0));
		}
		queueMap.put("currentOrder", list);
		return SUCCESS;
	}

	public String getMerchantQueue4Owner(){
		try{
			queueResult = service.getMerchantQueue4Owner(merchantId);
			ActionContext.getContext().put("merchantId",merchantId);
			ActionContext.getContext().put("queuelist",queueResult);
			this.setTableTypes(getTableTypes(queueResult));
			ActionContext.getContext().put("typelist",this.getTableTypes());
			return SUCCESS;
		}catch (Exception e){
			System.out.println(e);
			return FAIL;
		}

	}

	//对queue表中的tabletype去重
	public List<String> getTableTypes(List<Queue> merchantqueue){
		List<String> list = new LinkedList<String>();
		if(merchantqueue.size()>0){
			for(Queue a:merchantqueue){
				if(!list.contains(a.getTableType())) {
					list.add(a.getTableType());
				}
			}
		}
		//System.out.println(list.size());
		return list;
	}


	public String SearchQueue(){
		try{
			
			if(orderNum.equals(""))
				orderNum = "-1";
			queueResult = service.userSearchQueue(merchantId, Integer.parseInt(orderNum), tableType);
		 
			ActionContext.getContext().put("queuelist",queueResult);
			ActionContext.getContext().put("typelist",this.getTableTypes());
			return SUCCESS;
		}catch(Exception e){
			System.out.println(e);
			return FAIL;
		}
	}

	public String insertQueue(){
		queueBean = service.insertQueue(merchantId, userId, tableType);
		return SUCCESS;
	}

	public String getQueue(){
		queueResult = service.getQueue(merchantId, tableType, status, count);
		return SUCCESS;
	}

	public String checkQueue(){
		try{
		booleanResult = service.checkQueue(merchantId, Integer.parseInt(orderNum), tableType);
		return SUCCESS;
		}catch(Exception e){
			return FAIL;
		}
	}

	public String getQueueToNotice(){
		queueResult = service.getQueueToNotice(merchantId, tableType, queueToNotice);
		return SUCCESS;
	}

	public Integer getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUsrId(Integer userId) {
		this.userId = userId;
	}

	public String getTableType() {
		return tableType;
	}

	public void setTableType(String tableType) {
		this.tableType = tableType;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public List<Queue> getQueueResult() {
		return queueResult;
	}

	public void setQueueResult(List<Queue> queueResult) {
		this.queueResult = queueResult;
	}

	public Boolean getBooleanResult() {
		return booleanResult;
	}

	public void setBooleanResult(Boolean booleanResult) {
		this.booleanResult = booleanResult;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public void setQueueToNotice(List<Integer> queueToNotice) {
		this.queueToNotice = queueToNotice;
	}

	public Integer getOrderResult() {
		return orderResult;
	}

	public void setOrderResult(Integer orderResult) {
		this.orderResult = orderResult;
	}

	public QueueBean getQueueBean() {
		return queueBean;
	}

	public void setQueueBean(QueueBean queueBean) {
		this.queueBean = queueBean;
	}

	public Map<String, Object> getQueueMap() {
		return queueMap;
	}

	public void setQueueMap(Map<String, Object> queueMap) {
		this.queueMap = queueMap;
	}




}
