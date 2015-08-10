package com.t.core.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.t.core.entities.BCCollection;
import com.t.utils.BaseDao;

@Repository
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class BCCollectionDao extends BaseDao<BCCollection, Integer>{
	public BCCollectionDao(){
		this.entityClass = BCCollection.class;
	}

}
