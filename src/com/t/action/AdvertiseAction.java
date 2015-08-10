package com.t.action;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.t.bean.AdvertiseBean;
import com.t.core.entities.Advertise;
import com.t.service.interfaces.IAdvertiseService;
import com.t.service.interfaces.IMerchantService;
import com.t.utils.BaseAction;
import com.t.utils.ImageService;
import com.t.utils.SelectInfo;

public class AdvertiseAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private String adverId;
	//private String adverName;
	private String adverContent;
	private int toSex;
	private int toAge;
	private int toSalary; 
	private String merchantId;

	private File adverImage;
	private String[] merchantIds;

	private List<Advertise> adverList;
	private Advertise advertise;

	private String fileName; // 上传文件�
	private String oldPicture;

	private String imageFileName;
	private ImageService imageService;

	@Autowired
	private IAdvertiseService adservice;
	@Autowired
	private IMerchantService mservice;
	//android端用于显示用户订阅的商家广告
	private Integer userId;
	private List<AdvertiseBean> results;


	public String fetchSubscription(){
		results = adservice.getMyAdvertises(userId);
		result.put("results", results);
		result.put(STATE, SUCCESS);
		return SUCCESS;
	}
	// 转换图片名称
	public String getExtention(String fileName) {
		int pos = fileName.lastIndexOf(".");
		return new Date().getTime() + fileName.substring(pos);
	}

	/*添加广告*/
	@SuppressWarnings("static-access")
	public String advertiseAdd(){
		File imageFile = null;
		try{
			if(null!=fileName){
				imageFileName = getExtention(fileName) ;  //获得唯一的名�
				imageFile = new File(ServletActionContext.getServletContext().getRealPath("AdverUploadImages")+ "/" + imageFileName);   
				imageService.copy(adverImage, imageFile);  //把图片写入到上面设置的路径里  
			}else{
				imageFileName="000000.jpg";
			}

			Advertise ad = new Advertise();
			for(int i=0;i<merchantIds.length;i++){
				ad.setMerchantOwnerId(this.getMerchantOwner().getOwnerId());
				ad.setMerchantId(Integer.parseInt(merchantIds[i]));
				ad.setContent(adverContent.trim());
				ad.setToAgelevel(toAge);
				ad.setToSex(toSex);
				ad.setToSalary(toSalary);
				ad.setPicture(imageFileName);
				//还有fromdate和todate没有�
				adservice.advertiseAdd(ad);
			}
			return "addsucceed";
		}catch (Exception e){
			return "addfailed";
		}
	}


	/*查找登录用户发的广告*/
	public String getAllAdvers(){
		//System.out.println(this.getMerchantOwner().getOwnerId());
		try{
			this.adverList = adservice.findMyAds(this.getMerchantOwner().getOwnerId());
			if(adverList != null && this.getMymerchantlist()!=null){
				ActionContext.getContext().put("adverlist",adverList);
				ActionContext.getContext().put("shoplist", this.getMymerchantlist());
				return "adverList";
			}else{
				return "advernull";
			}			
		}catch (Exception e){
			return "advernull";
		}		

	}

	/*通过id获得对应实体，进入单个广告详细情况页*/
	public String findAdverById(){
		this.advertise = adservice.findAdById(Integer.parseInt(adverId)) ;
		if (advertise != null) {
			ActionContext.getContext().put("toage",SelectInfo.age[advertise.getToAgelevel()]);
			ActionContext.getContext().put("tosex",SelectInfo.sex[advertise.getToSex()]);
			ActionContext.getContext().put("toincome",SelectInfo.income[advertise.getToSalary()]);
			ActionContext.getContext().put("advertise",advertise);//一个对�
			return "findSuccess";
		} else {
			return "NotExist";
		}	
	}

	/*通过id获得对应实体，进入查询结果页*/
	public String queryAdverById(){
		this.advertise = adservice.findAdById(Integer.parseInt(adverId)) ;
		if (advertise != null) {
			ActionContext.getContext().put("adverlist",advertise);//一个对�
			return "querySuccess";
		} else {
			return "NotExist";
		}	
	}

	/*删除广告*/
	public String deleteAdverById() {	
		try{
			adservice.deleteadById(Integer.parseInt(adverId));
			return "deleteSuccess";	 	
		}catch (Exception e){
			return "deletefailed";
		}
	}

	/*更新广告*/
	@SuppressWarnings("static-access")
	public String updateAdver(){
		File imageFile = null;
		try{
			if(null!=fileName){
				imageFileName = getExtention(fileName) ;  //获得唯一的名�
				imageFile = new File(ServletActionContext.getServletContext().getRealPath("AdverUploadImages")+ "/" + imageFileName);   
				imageService.copy(adverImage, imageFile);  //把图片写入到上面设置的路径里  
			}else{
				imageFileName = oldPicture;
			}

			Advertise ad = adservice.findAdById(Integer.parseInt(adverId));
			//ad.setAdName(adverName);
			ad.setContent(adverContent);
			ad.setToAgelevel(toAge);
			ad.setToSex(toSex);
			ad.setToSalary(toSalary);
			ad.setPicture(imageFileName);
			//还有fromdate和todate没有�

			adservice.updateAdvertise(ad);
			ActionContext.getContext().put("toage",SelectInfo.age[ad.getToAgelevel()]);
			ActionContext.getContext().put("tosex",SelectInfo.sex[ad.getToSex()]);
			ActionContext.getContext().put("toincome",SelectInfo.income[ad.getToSalary()]);
			ActionContext.getContext().put("advertise",ad);//一个对�
			return "updatesucceed";
		}catch (Exception e){
			System.out.println(e);
			return "updatefailed";
		}
	}


	/*前台综合搜索*/
	public String IntegratedSearch(){
		try{
			adverId += "-1";
			merchantId += "-1";
			adverList = adservice.integratedSearch(adverId,merchantId,this.getMerchantOwner().getOwnerId());
			ActionContext.getContext().put("shoplist", this.getMymerchantlist());
			ActionContext.getContext().put("adverlist",adverList);
			return SUCCESS;
		}catch(Exception e){
			return FAIL;
		}
	}

	/*添加广告之前先获得商铺列表*/
	public  String AdvertiseAddPre(){
		//servletResponse.setContentType("text/html");
	
		try{
			if( this.getMymerchantlist()!=null){
				ActionContext.getContext().put("shoplist", mservice.integratedSearch("-1","-1", "-1", this.getMyshopids()));
				return SUCCESS;
			}else{
				return EMPTY;
			}
		}catch(Exception e)
		{
			System.out.println(e);
			return FAIL;
		}
	}

	public String getOldPicture() {
		return oldPicture;
	}

	public void setOldPicture(String oldPicture) {
		this.oldPicture = oldPicture;
	}

	public String getAdverId() {
		return adverId;
	}

	public void setAdverId(String adverId) {
		this.adverId = adverId;
	}

	public int getToSex() {
		return toSex;
	}

	public void setToSex(int toSex) {
		this.toSex = toSex;
	}

	public int getToAge() {
		return toAge;
	}

	public void setToAge(int toAge) {
		this.toAge = toAge;
	}

	public int getToSalary() {
		return toSalary;
	}

	public void setToSalary(int toSalary) {
		this.toSalary = toSalary;
	}

	public String getAdverContent() {
		return adverContent;
	}
	public void setAdverContent(String adverContent) {
		this.adverContent = adverContent;
	}

	public File getAdverImage() {
		return adverImage;
	}
	public void setAdverImage(File adverImage) {
		this.adverImage = adverImage;
	}
	public String getFileName() {
		return fileName;
	}
	public void setAdverImageFileName(String fileName) {
		this.fileName = fileName;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public List<AdvertiseBean> getResults() {
		return results;
	}
	public void setResults(List<AdvertiseBean> results) {
		this.results = results;
	}

	public String[] getMerchantIds() {
		return merchantIds;
	}

	public void setMerchantIds(String[] merchantIds) {
		this.merchantIds = merchantIds;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}




}
