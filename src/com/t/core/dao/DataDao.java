package com.t.core.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.t.core.entities.Data;
import com.t.utils.BaseDao;

@Repository
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class DataDao extends BaseDao<Data, Integer>{
	public DataDao(){
		this.entityClass = Data.class;
	}

}
