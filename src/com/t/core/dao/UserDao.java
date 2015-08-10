package com.t.core.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.t.core.entities.User;
import com.t.utils.BaseDao;


@Repository
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserDao extends BaseDao<User, Integer>{
	public UserDao(){
		this.entityClass = User.class;
	}
}
