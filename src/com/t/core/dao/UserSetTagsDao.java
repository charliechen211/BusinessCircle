package com.t.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.t.core.entities.Tag;
import com.t.core.entities.UserSetTags;
import com.t.utils.BaseDao;

@Repository
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserSetTagsDao extends BaseDao<UserSetTags,Integer>{
	
	public UserSetTagsDao(){
		this.entityClass = UserSetTags.class;
	}
	
	public int lookUpTag(int userId, int tagId){
		List<UserSetTags> list = new ArrayList<UserSetTags>();
		list = this.findByCriteria(Restrictions.eq("userId", Integer.valueOf(userId)),
				Restrictions.eq("tagId", Integer.valueOf(tagId)));
		if(list.isEmpty())
			return -1;
		return list.get(0).getTagId();
	}

}
