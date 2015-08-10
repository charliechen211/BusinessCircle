package com.t.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.t.core.entities.Tag;
import com.t.utils.BaseDao;


@Repository
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class TagDao extends BaseDao<Tag, Integer> {
	public TagDao()
	{
		this.entityClass = Tag.class;
	}
	
	public int lookUpTag(String tag){
		List<Tag> list = new ArrayList<Tag>();
		list = this.findByProperty("tagName", tag);
		if(list.isEmpty())
			return -1;
		return list.get(0).getTagId();
	}
	
}
