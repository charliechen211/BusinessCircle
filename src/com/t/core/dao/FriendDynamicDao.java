package com.t.core.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.t.core.entities.FriendDynamic;
import com.t.utils.BaseDao;

@Repository
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class FriendDynamicDao extends BaseDao<FriendDynamic, Integer>{
	public FriendDynamicDao(){
		this.entityClass = FriendDynamic.class;
	}

}
