package com.t.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.t.core.dao.MerchantTableInfoDao;
import com.t.core.entities.MerchantTableInfo;
import com.t.service.interfaces.IMerchantTableManagerService;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class MerchantTableManagerService implements IMerchantTableManagerService{

	@Autowired
	private MerchantTableInfoDao tableDao;

	@Override
	public Boolean addMerchantTableInfo(MerchantTableInfo table) {
		// TODO Auto-generated method stub
		tableDao.save(table);
		return true;
	}

	//根据商铺id获得其桌位列表
	public List<MerchantTableInfo> findByMerchantId(int merchantId) {
		List<MerchantTableInfo> list = tableDao.findByMerchantId(merchantId);
		//System.out.println(list.size());
		return list;
	}

	@Override
	public Boolean modifyMerchantTableInfo(MerchantTableInfo table) {
		/*List<MerchantTableInfo> list = this.findByMerchantId(String.valueOf(table.getMerchantId()));
		if(list.size()<0)
			return false;
		table.setMtiId(list.get(0).getMtiId());*/
		try{
			tableDao.saveOrUpdate(table);
			return true;
		}catch (Exception e){
			return false;
		}
	}

	@Override
	public Boolean removeMerchantTableInfo(MerchantTableInfo table) {
		try{
			tableDao.delete(table);
			return true;
		}catch (Exception e){
			return false;
		}
	}

	@Override
	public MerchantTableInfo findTableById(int tableid) { 
		return tableDao.findUniqueByProperty("mtiId", tableid);
	}

}
