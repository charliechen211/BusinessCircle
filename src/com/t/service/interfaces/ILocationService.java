package com.t.service.interfaces;

import java.util.List;

import com.t.core.entities.Location;


public interface ILocationService {
	public List<Location> fetchLocations(String select,Integer pageId, Integer pageSize);

}
