package com.t.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.t.core.entities.Advertise;
import com.t.utils.BaseDao;

@Repository
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class AdvertiseDao extends BaseDao<Advertise,Integer> {
	private static final Logger log = LoggerFactory.getLogger(ItemDao.class);
	
	public AdvertiseDao () {
		this.entityClass = Advertise.class;
	}
	//查找返回merchantIds的所有广告
	@SuppressWarnings("unchecked")
	public List<Advertise> getAdvertisesByMerchantIdList(List<Integer> list){
		List<Advertise> result = new ArrayList<Advertise>();
		if(list.size() == 0)
			return result;
		result = this.createQuery("from Advertise a where a.merchantId in (:list)").setParameterList("list", list).list();
		return result;
	}
}
