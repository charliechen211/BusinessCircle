package com.t.core.dao;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.t.core.entities.Merchant;
import com.t.core.entities.MerchantOwner;
import com.t.utils.BaseDao;
import com.t.utils.StringUtils;

@Repository
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class MerchantOwnerDao extends BaseDao<MerchantOwner, Integer> {
	
	
	
	public MerchantOwnerDao()
	{
		this.entityClass = MerchantOwner.class;
	}
	

	
	

	
}
