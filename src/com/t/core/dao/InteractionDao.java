package com.t.core.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.t.core.entities.Interaction;
import com.t.utils.BaseDao;

@Repository
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class InteractionDao extends BaseDao<Interaction,Integer> {
	
	public InteractionDao () {
		this.entityClass = Interaction.class;
	}
	
}
