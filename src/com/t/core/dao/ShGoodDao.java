package com.t.core.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.t.core.entities.ShGood;
import com.t.utils.BaseDao;

@Repository
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ShGoodDao extends BaseDao<ShGood, Integer> {
	
	public ShGoodDao(){
		this.entityClass = ShGood.class;
	}

}
