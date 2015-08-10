package com.t.core.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.t.core.entities.SchoolNews;
import com.t.utils.BaseDao;

@Repository
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class SchoolNewsDao extends BaseDao<SchoolNews,Integer> {
	
	public SchoolNewsDao () {
		this.entityClass = SchoolNews.class;
	}
	
}
