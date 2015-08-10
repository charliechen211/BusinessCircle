package com.t.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.t.core.dao.OmmappingDao;
import com.t.core.entities.Merchant;
import com.t.core.entities.Ommapping;
import com.t.service.interfaces.IOmmappingService;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class OmmappingService implements IOmmappingService {

	@Autowired
	private OmmappingDao ommDao;
	
	public void addUser(Ommapping mo) {
		ommDao.save(mo);
	}
	 
	public Vector<Integer> getMyshopIds(int ownerid) {
		 Vector<Integer> myshopsid = new Vector<Integer>(); 
		 List<Ommapping> om = new ArrayList<Ommapping>();
		 om = ommDao.findByProperty("ownerId", ownerid);
		 for(int i = 0;i<om.size();i++){
			 myshopsid.add(om.get(i).merchantId);
		 }
		return myshopsid;
	}

	@Override
	public void deleteByshopId(int shopid) {
		System.out.print("**********************"+shopid);
		Ommapping omm = ommDao.findUniqueByProperty("merchantId",shopid);
		ommDao.delete(omm);
	}

	
}
