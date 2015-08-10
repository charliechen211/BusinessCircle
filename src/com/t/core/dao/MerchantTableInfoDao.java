package com.t.core.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;
import com.t.core.entities.MerchantTableInfo;
import com.t.utils.BaseDao;

@Repository
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class MerchantTableInfoDao extends BaseDao<MerchantTableInfo, String> {
	private static final Logger log = LoggerFactory.getLogger(MerchantTableInfo.class);
	
	public static final String MERCHANTID = "merchantId";
	public static final String TABLETYPE = "tableType";
	public static final String DESCRIPTION = "description";
	public static final String STARTTIME = "startTime";
	public static final String ENDTIME = "endTime";
	
	public MerchantTableInfoDao(){
		this.entityClass = MerchantTableInfo.class;
	}
	
	public List<MerchantTableInfo> findByMerchantId(int merchantId){
		//System.out.println("merchantID "+merchantId);
		return this.findByProperty(MERCHANTID,merchantId);
	}

}
