package com.t.service.impl;

import java.util.List;
import java.util.Vector;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.t.core.dao.MerchantDao;
import com.t.core.dao.MerchantOwnerDao;
import com.t.core.entities.Merchant;
import com.t.core.entities.MerchantOwner;
import com.t.service.interfaces.IMerchantOwnerService;
import com.t.utils.Constants;
import com.t.utils.StringUtils;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class MerchantOwnerService implements IMerchantOwnerService{
	
	@Autowired
	public MerchantOwnerDao moDao;
	
	@Autowired
	private MerchantDao merchantDao; 
	
	public MerchantOwnerDao getMoDao() {
		return moDao;
	}

	public void setMoDao(MerchantOwnerDao moDao) {
		this.moDao = moDao;
	}

	public void Reguser(MerchantOwner mo) {
		moDao.save(mo);
	}
	
	/*登录时验证*/
	public boolean verifyUser(String managerName, String password) {
		  MerchantOwner mo = new MerchantOwner();
		  mo = moDao.findUniqueByProperty("ownerName", managerName);
		  if(mo!=null){
		  if(StringUtils.equals(mo.getPasswd(), password)&&mo.getCheckStatus().equals(Constants.BC_PASS)){  //必须是审核通过的
			  return true;
		  }
		      return false;
		  }else
			  return false;
	}

	public MerchantOwner findByName(String name) {
		MerchantOwner mo = new MerchantOwner();
		mo = moDao.findUniqueByProperty("ownerName",name );
		if(mo!=null)
			return mo;
		else 
		    return null;
	}

	//更新
	public void UpdateOwner(MerchantOwner owner) {
		moDao.saveOrUpdate(owner);	
	}

	@Override
	public List<MerchantOwner> checkList() {
		return moDao.findByProperty("checkStatus", Constants.BC_TOBE_CHECKED);
	}

	@Override
	public int check(Integer ownerId, String status) {
		MerchantOwner mo = new MerchantOwner();
		try {
			mo = moDao.findUniqueByProperty("ownerId",ownerId);
			System.out.println(mo.getOwnerName());
			mo.setCheckStatus(status);
			moDao.saveOrUpdate(mo);
			return 1;
		} catch (Exception e) {
			System.out.print("*******"+e);
			return 0;
		}
	}

	@Override
	public List<Merchant> refuseList(Vector<Integer> myshops) {	
		Query q = merchantDao.createQuery("from Merchant e where e.merchantId in (:ids) and e.checkStatus =:status");
		q.setString("status",Constants.BC_REFUSE);
		q.setParameterList("ids", myshops);
		return q.list();	
	}
	
	
 
}
