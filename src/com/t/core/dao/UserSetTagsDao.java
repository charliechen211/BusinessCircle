package com.t.core.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.t.core.entities.UserSetTags;
import com.t.utils.BaseDao;

@Repository
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserSetTagsDao extends BaseDao<UserSetTags,Integer>{
	
	public UserSetTagsDao(){
		this.entityClass = UserSetTags.class;
	}

}
