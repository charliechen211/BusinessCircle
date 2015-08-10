package com.t.core.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.t.core.entities.TagRecord;
import com.t.utils.BaseDao;

@Repository
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class TagRecordDao extends BaseDao<TagRecord, Integer>{
	public TagRecordDao()
	{
		this.entityClass = TagRecord.class;
	}
}
