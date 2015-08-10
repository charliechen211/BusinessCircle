package com.t.core.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.t.core.entities.Location;
import com.t.utils.BaseDao;

@Repository
@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
public class LocationDao extends BaseDao <Location, Integer>{
	public LocationDao(){
		this.entityClass = Location.class;
	}
	
}
