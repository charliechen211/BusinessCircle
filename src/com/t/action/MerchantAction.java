package com.t.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Vector;

import com.opensymphony.xwork2.ActionContext;
import com.t.bean.MerchantBean;
import com.t.core.entities.Merchant;
import com.t.core.entities.Ommapping;
import com.t.service.interfaces.IMerchantService;
import com.t.service.interfaces.IOmmappingService;
import com.t.service.interfaces.ITagService;
import com.t.utils.BaseAction;
import com.t.utils.ImageService;

public class MerchantAction extends BaseAction{

	private static final long serialVersionUID = 344079081937426944L;
	//android用户查询商圈内商铺
	private Integer circleId;
	private Integer pageId;
	private Integer pageSize;
	private Integer typeId;
	private MerchantBean merchantBean;
	private Integer userId;
	private String content;

	private String merchantName;
	private String merchantBranch;
	private String merchantDescription;
	private String merchantTelnumber ;
	private String merchantAddress;
	private Integer merchantTypeid;
	private String merchantTags;
	private File  shopImage;
	//经纬度
	private Double longitude;
	private Double latitude;
	

	private String fileName; // 上传文件名
	private String oldPicture;//更新图片时保存较早版本的图片
	private String imageFileName;

	private Merchant me;
	private List<Merchant> merchantList;
	private List<MerchantBean> merchantBeans;

	private String merchantId;//查看和删除映射

	private ImageService imageService;

	@Autowired
	private IMerchantService mservice;
	@Autowired
	private IOmmappingService omservice;

	private String shopImageServerUrl;
	
	private String checkStatus;
	
	//android 客户端功能
	//获取商家详细信息
	public String fetchMerchantBean(){
		merchantBean = mservice.fetchMerchantBean(Integer.valueOf(merchantId), typeId,userId);
		result.put("result",merchantBean);
		result.put(STATE,SUCCESS);
		return SUCCESS;
	}
	
	//根据商圈Id、typeId 获取商圈内相应的商家
	public String fetchMerchantBeans(){
		merchantBeans = mservice.fetchMerchantBeans(circleId,pageId,pageSize,userId,typeId);
		result.put("results",merchantBeans);
		result.put(STATE,SUCCESS);
		return SUCCESS;
	}
	@Autowired
	private ITagService tagservice;//添加tag

	// 转换图片名称
	public String getExtention(String fileName) {	 
		int pos = fileName.lastIndexOf(".");
		return new Date().getTime() + fileName.substring(pos);
	}

	// 添加商户
	@SuppressWarnings("static-access")
	public String shopAdd() {
		List<String> tags = new ArrayList<String>();
		Merchant shop = new Merchant();
		Ommapping om = new Ommapping();
		File imageFile = null;
		int newmerchantId;
		try{
			if(null!=fileName){
				imageFileName = getExtention(fileName) ;  //获得唯一的名称
				imageFile = new File(ServletActionContext.getServletContext().getRealPath("ShopUploadImages")+ "/" + imageFileName);   
				imageService.copy(shopImage, imageFile);  //把图片写入到上面设置的路径里  
			}else{
				imageFileName="000000.jpg";
			}			

			String temp[] = merchantTags.split(";");
			for(int i=0;i<temp.length;i++){
				tags.add(temp[i]);
			}

			shop.setMerchantName(merchantName.trim());
			shop.setBranch(merchantBranch.trim());
			shop.setDescription(merchantDescription.trim());
			shop.setTelNumber(merchantTelnumber.trim());
			shop.setAddress(merchantAddress.trim());
			shop.setType(merchantTypeid);
//			shop.setLongitude(0.0);
//			shop.setLatitude(0.0);
			shop.setCircle(circleId); //商圈
			shop.setCheckStatus("0");
			shop.setPicture(imageFileName);		
			
			shop.setLatitude(latitude); //经纬度
			shop.setLongitude(longitude);

			newmerchantId = mservice.shopAdd(shop);//添加的同时获得最新的商铺id
			/*更新持久对象*/
			this.getMyshopids().add(newmerchantId);
			this.setMymerchantlist(mservice.findByOwnerId(this.getMyshopids()));
			//将新建的商铺的id和创建者的id写入OMmaping表中
			om.setMerchantId(newmerchantId);
			om.setOwnerId(this.getMerchantOwner().getOwnerId());
			omservice.addUser(om);

			//将tags添加进对应的表中
			tagservice.insertTag(0,newmerchantId,(long)1,tags);

			return "shopAddSuccess";
		}catch(Exception e){
			System.out.println(e);
			return "error";
		}
	}

	public String shopFindById(){//进入shopMessage.jsp页面用的

		this.me = mservice.shopFindByShopId(merchantId);
		if (me != null) {
			ActionContext.getContext().put("shop",me);
			return "shopFindSuccess";
		} else {
			return "shopNotExist";
		}
	}

	// 删除商家
	@SuppressWarnings("unchecked")
	public String deleteMerchant() {			 
		try{
			for (Iterator it = this.getMyshopids().iterator(); it.hasNext();) {
				String temp = String.valueOf(it.next());
				if(temp.equals(merchantId)){
					it.remove();
					break;
				}
			}

			for (Iterator it = this.getMymerchantlist().iterator(); it.hasNext();) {
				Merchant tempshop = (Merchant)it.next();
				if(tempshop.getMerchantId()==Integer.parseInt(merchantId)){
					it.remove();
					break;
				}
			}
		}catch(Exception e){
			System.out.print(e);
		}	
		omservice.deleteByshopId(Integer.parseInt(merchantId));//删除商家后在ommaping表中也应该对应删除映射
		mservice.shopDelete(merchantId);

		return "deleteSuccess";	 
	}

	//更新商家数据
	@SuppressWarnings("static-access")
	public String updateMerchant(){
		File imageFile = null;
		try{
			if(null!=fileName){
				imageFileName = getExtention(fileName) ;  //获得唯一的名称
				imageFile = new File(ServletActionContext.getServletContext().getRealPath("ShopUploadImages")+ "/" + imageFileName);   
				imageService.copy(shopImage, imageFile);  //把图片写入到上面设置的路径里  
			}else{
				imageFileName=oldPicture;
			}			
			Merchant shop = mservice.shopFindByShopId(merchantId);

			shop.setMerchantName(merchantName);
			shop.setBranch(merchantBranch);
			shop.setDescription(merchantDescription);
			shop.setTelNumber(merchantTelnumber);
			shop.setAddress(merchantAddress);
			shop.setType(merchantTypeid);
			shop.setPicture(imageFileName);

			mservice.updateMerchant(shop);
			ActionContext.getContext().put("shop",shop);
			return "updateSuccess";
		}catch (Exception e){
			System.out.println(e);
			return "updatefailed";
		}
	}
	
	//管理员获得所有通过的商铺
	public String getAllShops(){
		List<Merchant> allList = mservice.getAllShops();
		if(allList!=null){
			ActionContext.getContext().put("merchantlist",allList);
			return SUCCESS;
		}
		return EMPTY;
	}

	//前台综合查询	
	public String IntegratedSearch(){
		try{
			merchantId += "-1";
			merchantName += "-1";
			merchantAddress += "-1";
			if(this.getMyshopids()!=null)
				merchantList = mservice.integratedSearch(merchantId.trim(), merchantName.trim(), merchantAddress.trim(), this.getMyshopids());
			ActionContext.getContext().put("merchantlist",merchantList);
			return SUCCESS;
		}catch(Exception e){
			System.out.println(e);
			return FAIL;
		}
	}
	//前台综合查询	
	public String IntegratedSearchAll(){
		try{
			merchantId += "-1";
			merchantName += "-1";
			merchantAddress += "-1";
			if(this.getMyshopids()!=null)
				merchantList = mservice.integratedSearchAll(merchantId.trim(), merchantName.trim(), merchantAddress.trim());
			ActionContext.getContext().put("merchantlist",merchantList);
			return SUCCESS;
		}catch(Exception e){
			System.out.println(e);
			return FAIL;
		}
	}

	//获得待审核商铺列表	
	public String ShopCheck(){
		try{
			List<Merchant> checkList = mservice.getCheckShops();
			if(checkList.size()>0){
				ActionContext.getContext().put("checkList",checkList);
				return SUCCESS;
			}else  
				return EMPTY;
		}catch(Exception e){
			System.out.println(e);
			return FAIL;
		}
	}
	
	//审核流程
	public String checkMerchant(){
		if(mservice.checkMerchant(merchantId, checkStatus)==1)
			return SUCCESS;
		else 
			return EMPTY;
	}

	
	public String getMerchantName() {
		return merchantName;
	}

	public String getOldPicture() {
		return oldPicture;
	}

	public void setOldPicture(String oldPicture) {
		this.oldPicture = oldPicture;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getMerchantTags() {
		return merchantTags;
	}

	public void setMerchantTags(String merchantTags) {
		this.merchantTags = merchantTags;
	}

	public String getMerchantBranch() {
		return merchantBranch;
	}

	public void setMerchantBranch(String merchantBranch) {
		this.merchantBranch = merchantBranch;
	}

	public String getMerchantDescription() {
		return merchantDescription;
	}

	public void setMerchantDescription(String merchantDescription) {
		this.merchantDescription = merchantDescription;
	}

	public String getMerchantTelnumber() {
		return merchantTelnumber;
	}

	public void setMerchantTelnumber(String merchantTelnumber) {
		this.merchantTelnumber = merchantTelnumber;
	}

	public String getMerchantAddress() {
		return merchantAddress;
	}

	public void setMerchantAddress(String merchantAddress) {
		this.merchantAddress = merchantAddress;
	}

	public Integer getMerchantTypeid() {
		return merchantTypeid;
	}

	public void setMerchantTypeid(Integer merchantTypeid) {
		this.merchantTypeid = merchantTypeid;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public File getShopImage() {
		return shopImage;
	}

	public void setShopImage(File shopImage) {
		this.shopImage = shopImage;
	}

	public String getFileName() {
		return fileName;
	}

	public void setShopImageFileName(String fileName) {
		this.fileName = fileName;
	}

	public ImageService getImageService() {
		return imageService;
	}

	public void setImageService(ImageService imageService) {
		this.imageService = imageService;
	}

	public IMerchantService getMservice() {
		return mservice;
	}

	public void setMservice(IMerchantService mservice) {
		this.mservice = mservice;
	}
	public Integer getCircleId() {
		return circleId;
	}

	public void setCircleId(Integer circleId) {
		this.circleId = circleId;
	}

	public List<MerchantBean> getMerchantBeans() {
		return merchantBeans;
	}

	public void setMerchantBeans(List<MerchantBean> merchantBeans) {
		this.merchantBeans = merchantBeans;
	}

	public Integer getPageId() {
		return pageId;
	}

	public void setPageId(Integer pageId) {
		this.pageId = pageId;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	public String getImageFileName() {
		return imageFileName;
	}
	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}
	public Merchant getMe() {
		return me;
	}
	public void setMe(Merchant me) {
		this.me = me;
	}
	public List<Merchant> getMerchantList() {
		return merchantList;
	}
	public void setMerchantList(List<Merchant> merchantList) {
		this.merchantList = merchantList;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public IOmmappingService getOmservice() {
		return omservice;
	}
	public void setOmservice(IOmmappingService omservice) {
		this.omservice = omservice;
	}
	public String getShopImageServerUrl() {
		return shopImageServerUrl;
	}
	public void setShopImageServerUrl(String shopImageServerUrl) {
		this.shopImageServerUrl = shopImageServerUrl;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public MerchantBean getMerchantBean() {
		return merchantBean;
	}
	public void setMerchantBean(MerchantBean merchantBean) {
		this.merchantBean = merchantBean;
	}

	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCheckStatus() {
		return checkStatus;
	}
	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	

}
