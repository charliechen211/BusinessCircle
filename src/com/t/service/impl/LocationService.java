package com.t.service.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.t.core.dao.LocationDao;
import com.t.core.entities.Location;
import com.t.service.interfaces.ILocationService;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
public class LocationService implements ILocationService {
	
	@Autowired
	private LocationDao locationDao;
	
	@SuppressWarnings("unchecked")
	public List<Location> fetchLocations(String select, Integer pageId, Integer pageSize){
		Query query = locationDao.createQuery("from Location l where l.locationName like :name");
		query.setString("name", "%"+select+"%");
		query.setFirstResult(pageId * pageSize);
		query.setMaxResults(pageSize);
		return query.list();
	}
	
}
