package com.t.core.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.t.core.entities.Type;
import com.t.utils.BaseDao;



@Repository
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class TypeDao extends BaseDao<Type, Integer>{
	public TypeDao()
	{
		this.entityClass = Type.class;
	}
}
