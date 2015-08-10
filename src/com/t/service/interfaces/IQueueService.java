package com.t.service.interfaces;

import java.util.List;
import java.util.Set;

import com.t.bean.QueueBean;
import com.t.core.entities.Queue;

public interface IQueueService {
	public QueueBean insertQueue(int merchantId, int userId, String tableType);
	public List<Queue> getQueue(int merchantId, String tableType, String status, int count);
	public Boolean checkQueue(int merchantId, int userId, String tableType);
	public List<Queue> getQueueToNotice(int merchantId, String tableType, List<Integer> inputList);
	public Integer getOrderNum(int merchantId, int userId, String tableType);
	public Queue getCurrentOrder(int merchantId, String tableType);
	public List<Queue> getMerchantQueue(int merchantId, int userId);
	public List<Queue> getUserQueue(int userId);
	public Set<Integer> getMerchantIdByUser(int userId);
	
	public List<Queue> getMerchantQueue4Owner(int MerchantId);
	public List<Queue> userSearchQueue(int merchantId,int orderNum,String tableType);
}
