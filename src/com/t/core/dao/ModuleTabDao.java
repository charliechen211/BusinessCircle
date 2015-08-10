package com.t.core.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.t.core.entities.ModuleTab;
import com.t.utils.BaseDao;

@Repository
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ModuleTabDao extends BaseDao<ModuleTab,Integer> {
	
	public ModuleTabDao () {
		this.entityClass = ModuleTab.class;
	}
	
	 
	
}
