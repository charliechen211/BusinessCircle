package com.t.action;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.t.bean.SchoolAroundItemBean;
import com.t.core.entities.SchoolAroundItem;
import com.t.service.interfaces.ISchoolAroundService;
import com.t.utils.BaseAction;
import com.t.utils.ImageService;


public class SchoolAroundAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	private Integer itemId;
	private int typeId;
	private int objectId;
	private String itemName;
	private String itemDescription;
	private String itemLocation;
	private String itemTel;
	private String itemPlus;
	
	private  String fenlei;
	private String shopId;

	private List<SchoolAroundItemBean> results;
	private SchoolAroundItemBean  bean;
	private Integer pageId;
	private Integer pageSize;

	//经纬度
	private Double longitude;
	private Double latitude;

	private String itemPic;
	private String fileName; // 上传文件名
	private File  shopImage; //上传文件
	
	private String oldPicture;//更新图片时保存较早版本的图片

	@Autowired
	private ISchoolAroundService schoolAroundServ;
	@Autowired
	private ImageService imageService;

	public String fetchItemList(){
		results = schoolAroundServ.getList(typeId,objectId,pageId,pageSize);
		result.put("results", results);
		result.put(STATE, SUCCESS);
		return SUCCESS;
	}

	public String fetchItem(){
		bean = schoolAroundServ.getItem(itemId);
		result.put("results", bean);
		result.put(STATE, SUCCESS);
		return SUCCESS;
	}

	// 转换图片名称
	public String getExtention(String fileName) {	 
		int pos = fileName.lastIndexOf(".");
		return new Date().getTime() + fileName.substring(pos);
	}

	//添加校园周边Item
	@SuppressWarnings("static-access")
	public String ItemAdd() {
		SchoolAroundItemBean bean = new SchoolAroundItemBean();
		File imageFile = null;

		try{
			if(null!=fileName){
				itemPic = getExtention(fileName) ;  //获得唯一的名称
				imageFile = new File(ServletActionContext.getServletContext().getRealPath("SchoolAroundImages")+ "/" + itemPic);   
				imageService.copy(shopImage, imageFile);  //把图片写入到上面设置的路径里  
			}
			else{
				itemPic="000000.jpg";
			}			

			bean.setItemName(itemName.trim());
			bean.setItemDescription(itemDescription.trim());
			bean.setItemTel(itemTel.trim());
			bean.setItemLocation(itemLocation.trim());
			System.out.println("********************"+fenlei.substring(1, 2));
			
			bean.setTypeId(Integer.parseInt(fenlei.substring(0, 1))); 
			bean.setObjectId(Integer.parseInt(fenlei.substring(1, 2)));
			bean.setLongitude(longitude);
			bean.setLatitude(latitude);
			bean.setItemPic(itemPic);
			bean.setItemPlus(itemPlus);

			schoolAroundServ.addItem(bean);    //添加校园周边Item			
			result.put("results", bean);
			result.put(STATE, SUCCESS);
			return SUCCESS;
		}catch(Exception e){
			System.out.println(e);
			return "error";
		}
	}
	
	//update校园周边Item
	@SuppressWarnings("static-access")
	public String ItemUpdate() {
		SchoolAroundItem item = schoolAroundServ.finById(itemId);
		File imageFile = null;
		
		try{
			if(null!=fileName){
				itemPic = getExtention(fileName) ;  //获得唯一的名称
				imageFile = new File(ServletActionContext.getServletContext().getRealPath("SchoolAroundImages")+ "/" + itemPic);   
				imageService.copy(shopImage, imageFile);  //把图片写入到上面设置的路径里  
			}
			else{
				itemPic= oldPicture;
			}			
			
			item.setItemName(itemName.trim());
			item.setItemDescription(itemDescription.trim());
			item.setItemTel(itemTel.trim());
			item.setItemLocation(itemLocation.trim());
//			item.setTypeId(typeId);
//			item.setObjectId(objectId);
//			item.setLongitude(longitude);
//			item.setLatitude(latitude);
			item.setItemPic(itemPic);
		//	item.setItemPlus(itemPlus);
			
			schoolAroundServ.updateItem(item);    //添加校园周边Item		
			SchoolAroundItemBean bean =  new SchoolAroundItemBean(item);
			ActionContext.getContext().put("shop",bean);
			result.put(STATE, SUCCESS);
			return SUCCESS;
		}catch(Exception e){
			System.out.println(e);
			return "error";
		}
	}

	public String getAllShops(){
		List<SchoolAroundItem> allShops = schoolAroundServ.getAllShops();
		if(allShops!=null){
			ActionContext.getContext().put("merchantlist",allShops);
			return SUCCESS;
		}else
			return EMPTY;				
	}

	public String IntegratedSearchAll(){
		shopId += "-1";
		itemName += "-1";
		itemLocation += "-1";
		List<SchoolAroundItem> allShops = schoolAroundServ.IntegratedSearchAll(shopId,itemName,itemLocation);
		if(allShops!=null){
			ActionContext.getContext().put("merchantlist",allShops);
			return SUCCESS;
		}else
			return EMPTY;				
	}

	public String  shopFindById(){
		SchoolAroundItemBean item = schoolAroundServ.getItem(itemId);
		
		if(item!=null){
			ActionContext.getContext().put("shop",item);
			return SUCCESS;
		}else
			return EMPTY;		
	}



	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public int getObjectId() {
		return objectId;
	}

	public void setObjectId(int objectId) {
		this.objectId = objectId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public String getItemPic() {
		return itemPic;
	}

	public void setItemPic(String itemPic) {
		this.itemPic = itemPic;
	}

	public String getItemLocation() {
		return itemLocation;
	}

	public void setItemLocation(String itemLocation) {
		this.itemLocation = itemLocation;
	}

	public String getItemTel() {
		return itemTel;
	}

	public void setItemTel(String itemTel) {
		this.itemTel = itemTel;
	}

	public File getShopImage() {
		return shopImage;
	}

	public void setShopImage(File shopImage) {
		this.shopImage = shopImage;
	}

	public String getItemPlus() {
		return itemPlus;
	}

	public void setItemPlus(String itemPlus) {
		this.itemPlus = itemPlus;
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

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
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

	public String getOldPicture() {
		return oldPicture;
	}

	public void setOldPicture(String oldPicture) {
		this.oldPicture = oldPicture;
	}
	public String getFenlei() {
		return fenlei;
	}

	public void setFenlei(String fenlei) {
		this.fenlei = fenlei;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	
	public String getFileName() {
		return fileName;
	}

	public void setShopImageFileName(String fileName) {
		this.fileName = fileName;
	}

}
