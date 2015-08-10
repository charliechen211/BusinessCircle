package com.t.action;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import com.opensymphony.xwork2.ActionContext;
import com.t.core.entities.MerchantOwner;
import com.t.service.interfaces.IMerchantOwnerService;
import com.t.service.interfaces.IMerchantService;
import com.t.service.interfaces.IOmmappingService;
import com.t.utils.BaseAction;
import com.t.core.entities.Merchant;

public class MerchantOwnerAction extends BaseAction{
	private static final long serialVersionUID = 1L;
	@Autowired
	private IMerchantOwnerService moservice;
	@Autowired
	private IMerchantService  mservice;
	@Autowired
	private IOmmappingService omservice;

	private String ownerPassword;
	private String ownerTelnumber;
	private String ownerIdnumber;

	private String oldPassword;
	private String newPassword1;
	private String newPassword2;

	public String managerName;
	public MerchantOwner mo = new MerchantOwner();
	public List<Merchant> merchantList;
	
	public String ownerId;
	public String checkStatus;

	public String regUser(){ //注册商家用户
		MerchantOwner mo = new MerchantOwner();		

		mo.setOwnerName(managerName);
		mo.setPasswd(ownerPassword);
		mo.setTelNumber(ownerTelnumber);
		mo.setIdNumber(ownerIdnumber);	
		mo.setCheckStatus("0");
		try{
			moservice.Reguser(mo);	
			return SUCCESS;	
		}catch (Exception e){
			return FAIL;	
		}
	}

	//登录
	@SuppressWarnings("unchecked")
	public String Login(){	
		boolean result = moservice.verifyUser(managerName.trim(), ownerPassword.trim());	
		if(result){
			mo = moservice.findByName(managerName);
			this.setMerchantOwner(mo);//记录登陆用户
			this.setMyshopids(omservice.getMyshopIds(mo.getOwnerId()));//获得登录用户的商家ids
			this.setMymerchantlist(mservice.findByOwnerId(this.getMyshopids()));
			this.setLogFlag("yes");
			session.put("user", mo);
			if(managerName.trim().equals("admin"))
				return Admin;
			if(managerName.trim().equals("campus"))
				return CMAPUS;
			else 
				return SUCCESS;
		}else{
			return FAIL;
		}		
	}

	// 商铺列表	 
	public String getAllList(){
		if(this.getMyshopids().size()!=0){		
			ActionContext.getContext().put("merchantlist",mservice.integratedSearch("-1", "-1", "-1", this.getMyshopids()));
			return "merchantList";
		}
		else 
			return "merchantnull";
	}

	/*获得个人信息*/
	public String getLoginUser(){
		try{
			ActionContext.getContext().put("owner", this.getMerchantOwner());
			return SUCCESS;
		}catch (Exception e){
			return FAIL;
		}
	}

	/*更新信息*/
	public String ModifyInfo(){
		try{
			this.getMerchantOwner().setOwnerName(managerName);
			this.getMerchantOwner().setIdNumber(ownerIdnumber);
			this.getMerchantOwner().setTelNumber(ownerTelnumber);
			moservice.UpdateOwner(this.getMerchantOwner());
			return SUCCESS;
		}catch(Exception e){
			return FAIL;
		}
	}

	//修改密码
	public String ModifyPswd(){
		try{
			if(!oldPassword.equals(this.getMerchantOwner().getPasswd())) 
				return "InvalidatePwd";
			else{
				this.getMerchantOwner().setPasswd(newPassword1);
				moservice.UpdateOwner(this.getMerchantOwner());
				return SUCCESS;
			}
		}catch(Exception e){
			return FAIL;
		}
	}

	public String MerchantList4Analysis(){
		if(this.getMyshopids().size()!=0){			
			ActionContext.getContext().put("merchantlist",this.getMymerchantlist());
			return "merchantList";
		}
		else 
			return "merchantnull";
	}

	//获得待审核注册人员列表
	public String ownerCheck(){
		List<MerchantOwner> chList = moservice.checkList();
		if(chList.size()>0){
			ActionContext.getContext().put("checklist",chList);
			return SUCCESS;
		}else {
			return EMPTY;
		}
	}
	
	//审核流程
	public String check(){
		System.out.println(ownerId+"*****"+checkStatus);
		if(moservice.check(Integer.parseInt(ownerId), checkStatus)==1){
			return SUCCESS;
		}else {
			return FAIL;
		}
	}
	
	//获得被拒绝商铺列表
	public String RefuseList(){
		if(this.getMyshopids().size()>0){
		List<Merchant> refuseList = moservice.refuseList(this.getMyshopids());
		if(refuseList.size()>0){
			ActionContext.getContext().put("refuseList",refuseList);
			return SUCCESS;
		}else {
			return EMPTY;
		}
		}else {
			return EMPTY;
		}
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getOwnerPassword() {
		return ownerPassword;
	}

	public void setOwnerPassword(String ownerPassword) {
		this.ownerPassword = ownerPassword;
	}

	public String getOwnerTelnumber() {
		return ownerTelnumber;
	}

	public void setOwnerTelnumber(String ownerTelnumber) {
		this.ownerTelnumber = ownerTelnumber;
	}

	public String getOwnerIdnumber() {
		return ownerIdnumber;
	}

	public void setOwnerIdnumber(String ownerIdnumber) {
		this.ownerIdnumber = ownerIdnumber;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword1() {
		return newPassword1;
	}

	public void setNewPassword1(String newPassword1) {
		this.newPassword1 = newPassword1;
	}

	public String getNewPassword2() {
		return newPassword2;
	}

	public void setNewPassword2(String newPassword2) {
		this.newPassword2 = newPassword2;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}

}
