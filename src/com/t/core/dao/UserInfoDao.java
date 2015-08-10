package com.t.core.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.t.core.entities.UserInfo;
import com.t.utils.BaseDao;

@Repository
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserInfoDao extends BaseDao<UserInfo, Integer>{
	public UserInfoDao(){
		this.entityClass = UserInfo.class;
	}
}
