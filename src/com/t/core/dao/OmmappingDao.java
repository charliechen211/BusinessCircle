package com.t.core.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.t.core.entities.Ommapping;
import com.t.utils.BaseDao;


@Repository
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class OmmappingDao extends BaseDao<Ommapping, Integer> {
	public OmmappingDao()
	{
		this.entityClass = Ommapping.class;
	}
}
