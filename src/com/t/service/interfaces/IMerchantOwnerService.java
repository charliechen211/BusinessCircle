package com.t.service.interfaces;

import java.util.List;
import java.util.Vector;

import com.t.core.entities.Merchant;
import com.t.core.entities.MerchantOwner;

public interface IMerchantOwnerService {
	//注册
	public void Reguser(MerchantOwner mo) ;
	 //登录判断
	public boolean verifyUser(String managerName, String password);
	
	public MerchantOwner findByName(String name);

	//更新数据
	public void UpdateOwner(MerchantOwner owner);
	
	public List<MerchantOwner> checkList();
	
	//审核流程
	public int check(Integer ownerId,String status);
	
	//获得被拒列表
	public List<Merchant> refuseList(Vector<Integer> myshops);
	
	
}
