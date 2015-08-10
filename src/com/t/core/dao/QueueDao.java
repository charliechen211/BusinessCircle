package com.t.core.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.tiles.jsp.taglib.GetAsStringTag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sun.xml.internal.bind.v2.model.core.ID;
import com.t.core.entities.Queue;
import com.t.utils.BaseDao;

@Repository
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class QueueDao extends BaseDao<Queue, String>{
	private static final Logger log = LoggerFactory.getLogger(QueueDao.class);
	
	public static final String MERCHANTID = "merchantId";
	public static final String USERID = "userId";
	public static final String TABLETYPE = "tableType";
	public static final String ORDERNUM = "orderNum";
	public static final String STATUS = "status";
	public static final String STARTTIME = "startTime";
	public static final String ENDTIME = "endTime";
	
	public QueueDao () {
		this.entityClass = Queue.class;
	}
	
	@SuppressWarnings("unchecked")
	public Integer insertQueue(int merchantId, int userId, String tableType){
		Map<String, Object> firstMap = new HashMap<String, Object>();
		firstMap.put("merchantId", merchantId);
		firstMap.put("userId", userId);
		firstMap.put("tableType", tableType);
		firstMap.put("status", "Waiting");
		List<Queue> list = this.findBySQL(
				"SELECT * from queue where userId = :userId " +
				"and merchantId = :merchantId and tableType = :tableType and status = :status", firstMap);
		if(list.size()!=0)
			return -1;
		this.flush();
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("merchantId", merchantId);
		//paramMap.put("userId", userId);
		paramMap.put("tableType", tableType);
		List<Queue> nowMaxOrder = this.findBySQL("SELECT * from queue where merchantId = :merchantId and tableType = :tableType order by orderNum desc limit 1", paramMap);
		Queue temp= null;
		if (nowMaxOrder!=null&&nowMaxOrder.size()>0) {
			temp = nowMaxOrder.get(0);
		}
		int nowQueue=0;
		if(temp == null)
			nowQueue = 1;
		else if(temp.getOrderNum()<0)
			return -1;
		else
			nowQueue = temp.getOrderNum()+1;
		
		Timestamp time = new Timestamp(System.currentTimeMillis());
		System.out.println("now to save");
		
		Queue jsoQueue = new Queue(merchantId, userId, tableType, nowQueue, "Waiting", time);
		String id  = String.valueOf(this.save(jsoQueue));
		String tmp = String.valueOf(id);
		
		this.flush();
		System.out.println("tmp "+tmp);
		return nowQueue;
	}
	
	@SuppressWarnings("unchecked")
	public List<Queue> getQueue(int userId){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", userId);
		paramMap.put("status", "Waiting");

		List<Queue> queueList = this.findBySQL(
				"SELECT * from queue where userId = :userId and status = :status", paramMap);
		
		return queueList;
	}
	
	@SuppressWarnings("unchecked")
	public List<Queue> getQueue(int userId, int merchantId){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", userId);
		paramMap.put("merchantId", merchantId);
		paramMap.put("status", "Waiting");

		List<Queue> queueList = this.findBySQL(
				"SELECT * from queue where merchantId = :merchantId and " +
				"userId = :userId and status = :status", paramMap);
		
		return queueList;
	}
	
	@SuppressWarnings("unchecked")
	public List<Queue> getQueue(int merchantId, String tableType, String status, int count){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("merchantId", merchantId);
		paramMap.put("tableType", tableType);
		paramMap.put("status", status);
		paramMap.put("count", count);

		List<Queue> queueList = this.findBySQL(
				"SELECT * from queue where merchantId = :merchantId and " +
				"tableType = :tableType and status = :status order by orderNum limit 0,:count", paramMap);
		
		return queueList;
		
	}
	
	@SuppressWarnings("unchecked")
	public Set<Integer> getMerchantIdByUser(int userId){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", userId);
		paramMap.put("status", "Waiting");

		List<Queue> queueList = this.findBySQL(
				"SELECT * from queue where userId = :userId and status = :status", paramMap);
		Set<Integer> list = new HashSet<Integer>();
		
		for(int i=0; i<queueList.size(); i++){
			list.add(queueList.get(i).getMerchantId());
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<Queue> checkQueue(int merchantId, int orderNum, String tableType){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		paramMap.put("merchantId", merchantId);
		paramMap.put("orderNum", orderNum);
		paramMap.put("tableType", tableType);
		List<Queue> list = this.findBySQL(
				"select * from queue where " +
				"merchantId = :merchantId and orderNum = :orderNum and tableType = :tableType", paramMap);
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<Queue> getQueueToNotice(int merchantId, String tableType, List<Integer> inputList){
		List<Queue> result = new ArrayList<Queue>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("merchantId", merchantId);
		paramMap.put("tableType", tableType);
		for(int i=0; i<inputList.size(); i++){
			int next = inputList.get(i);
			paramMap.put("num", next);
			paramMap.put("status", "Waiting");
			List<Queue> tmp = this.findBySQL(
					"SELECT * from queue where " +
					"merchantId = :merchantId and tableType = :tableType and status = :status " +
					"order by orderNum limit :num,1", paramMap);
			if(tmp.size()!=0)
				result.add(tmp.get(0));
		}
		return result;
	}

	
	@SuppressWarnings("unchecked")
	public Integer getOrderNum(int merchantId, int userId, String tableType){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("merchantId", merchantId);
		paramMap.put("userId", userId);
		paramMap.put("tableType", tableType);
		List<Integer> orderNum = this.findBySQL(
				"SELECT MAX(queue.orderNum) from queue where " +
				"merchantId = :merchantId and userId = :userId and tableType = :tableType", paramMap);
		if(orderNum.get(0)<0)
			return -1;
		
		return orderNum.get(0);
	}
	
	@SuppressWarnings("unchecked")
	public Queue getCurrentOrder(int merchantId, String tableType){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("merchantId", merchantId);
		paramMap.put("tableType", tableType);
		paramMap.put("status", "Waiting");
		List<Queue> currentOrder = this.findBySQL(
				"SELECT * from queue where merchantId = :merchantId and tableType = :tableType and status = :status " +
				" order by orderNum limit 1", paramMap);
		System.out.println(currentOrder.size());
		if(currentOrder.size()==0)
			return null;
		return currentOrder.get(0);
	}
}
