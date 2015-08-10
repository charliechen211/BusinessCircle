package com.t.service.impl;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.t.core.dao.CircleDao;
import com.t.core.entities.Circle;
import com.t.service.interfaces.ICircleService;
import com.t.service.interfaces.IShInfoService;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class CircleService implements ICircleService{
	
	@Autowired
	private CircleDao circleDao;
	
	public List<Circle> fetchCircleInfo() {
		List<Circle> list = circleDao.findByCriteria();
		return list;
	}
	
}
