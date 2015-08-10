package com.t.core.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.t.core.entities.BusinessCircleDynamic;
import com.t.utils.BaseDao;

@Repository
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class BusinessCircleDynamicDao extends BaseDao<BusinessCircleDynamic, Integer>{
	
	public BusinessCircleDynamicDao(){
		this.entityClass = BusinessCircleDynamic.class;
	}

}
