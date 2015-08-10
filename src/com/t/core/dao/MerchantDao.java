package com.t.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.t.core.entities.Merchant;
import com.t.utils.BaseDao;

/**
 * A data access object (DAO) providing persistence and search support for
 * Merchant entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.t.model.Merchant
 * @author MyEclipse Persistence Tools
 */

@Repository
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class MerchantDao extends BaseDao<Merchant, Integer> {
	private static final Logger log = LoggerFactory
			.getLogger(MerchantDao.class);
	// property constants
	public static final String MERCHANT_NAME = "merchantName";
	public static final String BRANCH = "branch";
	public static final String DESCRIPTION = "description";
	public static final String TEL_NUMBER = "telNumber";
	public static final String ADDRESS = "address";
	public static final String AVERAGE_CONSUME = "averageConsume";
	public static final String AVERAGE_VALUE = "averageValue";
	public static final String TYPE = "type";
	
	private HibernateTemplate hibernateTemplate;
	
	//与tagEntity表join
	public List<Merchant> joinWithTagEntity(String content){
		List<Merchant> merchants = new ArrayList<Merchant>();
		Criteria criteria = getCriteria();
		
		return merchants;
	}
	//通过Id删除商家
	public boolean merchantDeleteById(int merchantId) {
		Merchant mer = new Merchant();
		mer.setMerchantId(merchantId);
		try{
		hibernateTemplate.delete(mer);
		return true;
	}catch (Exception e){
		return false;
	}
	}
	
	public String getName(int merchantId){
		return this.findByProperty("mid", merchantId).get(0).getMerchantName();
		
	}
	

	public MerchantDao()
	{
		this.entityClass = Merchant.class;
	}
	
}