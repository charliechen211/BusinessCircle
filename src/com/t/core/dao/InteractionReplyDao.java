package com.t.core.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.t.core.entities.InteractionReply;
import com.t.utils.BaseDao;

@Repository
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class InteractionReplyDao extends BaseDao<InteractionReply,Integer> {
	
	public InteractionReplyDao () {
		this.entityClass = InteractionReply.class;
	}
	
}
