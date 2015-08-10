package com.t.service.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.t.bean.QueueBean;
import com.t.core.dao.QueueDao;
import com.t.core.entities.Queue;
import com.t.service.interfaces.IQueueService;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class QueueService implements IQueueService{

	@Autowired
	private QueueDao queueDao;

	@Override
	public Boolean checkQueue(int merchantId, int orderNum, String tableType) {
		// TODO Auto-generated method stub
		List<Queue> list = queueDao.checkQueue(merchantId, orderNum, tableType);
		if(list.size()==0)
			return false;
		else{
			for(int i=0; i<list.size(); i++){
				if(list.get(i)==null)
					System.out.println("NULL");

				Queue queue = list.get(i);
				queue.setStatus("Checked");
				queue.setEndTime(new Timestamp(System.currentTimeMillis()));
				queueDao.saveOrUpdate(queue);
			}
		}
		return true;
	}

	public Set<Integer> getMerchantIdByUser(int userId){
		return queueDao.getMerchantIdByUser(userId);
	}

	@Override
	public Queue getCurrentOrder(int merchantId, String tableType) {
		// TODO Auto-generated method stub
		return queueDao.getCurrentOrder(merchantId, tableType);
	}

	@Override
	public Integer getOrderNum(int merchantId, int userId, String tableType) {
		// TODO Auto-generated method stub
		return queueDao.getOrderNum(merchantId, userId, tableType);
	}

	@Override
	public List<Queue> getQueue(int merchantId, String tableType,
			String status, int count) {
		// TODO Auto-generated method stub
		return queueDao.getQueue(merchantId, tableType, status, count);
	}

	@Override
	public List<Queue> getQueueToNotice(int merchantId, String tableType,
			List<Integer> inputList) {
		// TODO Auto-generated method stub
		return queueDao.getQueueToNotice(merchantId, tableType, inputList);
	}

	@Override
	public QueueBean insertQueue(int merchantId, int userId, String tableType) {
		// TODO Auto-generated method stub
		return new QueueBean(queueDao.insertQueue(merchantId, userId, tableType), 
				queueDao.getCurrentOrder(merchantId, tableType).getOrderNum());
	}

	@Override
	public List<Queue> getMerchantQueue(int userId, int merchantId) {
		// TODO Auto-generated method stub
		return queueDao.getQueue(userId, merchantId);
	}

	@Override
	public List<Queue> getUserQueue(int userId) {
		// TODO Auto-generated method stub
		return queueDao.getQueue(userId);
	}

	//为商家端获得所有排队列表
	@SuppressWarnings("unchecked")
	public List<Queue> getMerchantQueue4Owner(int MerchantId) { 		 
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("merchantId", MerchantId);
		paramMap.put("status", "Waiting");
		List<Queue> currentOrder = queueDao.findBySQL(
				"SELECT * from queue where merchantId = :merchantId and status =:status ",paramMap);
		//System.out.println(currentOrder.size());	
		return currentOrder;
	}

	//用户搜索
	@SuppressWarnings("unchecked")
	public List<Queue> userSearchQueue(int merchantId, int orderNum,
			String tableType) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("merchantId", merchantId);
		paramMap.put("tableType",tableType);
		paramMap.put("state","Waiting");
		if(orderNum != -1){
			paramMap.put("orderNum",orderNum);
			return queueDao.findBySQL(
					"SELECT * from queue where merchantId = :merchantId and orderNum = :orderNum and tableType=:tableType and status =:state",paramMap);
		}else{
			return queueDao.findBySQL(
					"SELECT * from queue where merchantId = :merchantId and tableType=:tableType and status =:state",paramMap);
		}

	}

}
