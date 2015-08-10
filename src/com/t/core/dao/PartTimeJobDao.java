package com.t.core.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.t.core.entities.PartTimeJob;
import com.t.utils.BaseDao;

@Repository
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class PartTimeJobDao extends BaseDao<PartTimeJob,Integer> {
	
	public PartTimeJobDao () {
		this.entityClass = PartTimeJob.class;
	}
	
}
