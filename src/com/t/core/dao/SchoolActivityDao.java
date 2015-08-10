package com.t.core.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.t.core.entities.SchoolActivity;
import com.t.utils.BaseDao;

@Repository
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class SchoolActivityDao extends BaseDao<SchoolActivity,Integer>{
	public SchoolActivityDao () {
		this.entityClass = SchoolActivity.class;
	}

}
