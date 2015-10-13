package com.t.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.t.bean.MerchantBean;
import com.t.core.dao.BCCollectionDao;
import com.t.core.dao.MerchantDao;
import com.t.core.entities.BCCollection;
import com.t.service.interfaces.IRecommenderService;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class RecommenderServiceImpl implements IRecommenderService {
	
	@Autowired
	private MerchantDao merchantDao;
	@Autowired
	public BCCollectionDao BCCollecDao;

	@Override
	public List<MerchantBean> fetchRecMerchantBeans(int userId) {
		List<BCCollection> collections = new ArrayList<BCCollection>();
		collections = BCCollecDao.findByProperty("userId", userId);
		List<Integer> collectionList = new ArrayList<Integer>();
		for (BCCollection c: collections) {
			if (c.getEntityType() == 1) {
				collectionList.add(c.getEntityId());
			}
		}				
		return new ArrayList<MerchantBean>();
	}

}
