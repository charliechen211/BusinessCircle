package com.t.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.t.core.entities.UserSetTags;
import com.t.core.entities.UserTagLib;
import com.t.utils.BaseDao;

@Repository
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserTagLibDao extends BaseDao<UserTagLib,Integer>{
	
	public UserTagLibDao(){
		this.entityClass = UserTagLib.class;
	}
	
	public int lookUpTag(String tagName) {
		List<UserTagLib> list = new ArrayList<UserTagLib>();
		list = this.findByProperty("tagName", tagName);
		if(list.isEmpty())
			return -1;
		return list.get(0).getTagId();
	}

}
