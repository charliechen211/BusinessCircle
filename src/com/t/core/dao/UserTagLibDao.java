package com.t.core.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.t.core.entities.UserTagLib;
import com.t.utils.BaseDao;

@Repository
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserTagLibDao extends BaseDao<UserTagLib,Integer>{
	
	public UserTagLibDao(){
		this.entityClass = UserTagLib.class;
	}

}
