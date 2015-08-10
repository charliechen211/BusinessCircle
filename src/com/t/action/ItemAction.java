package com.t.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.t.bean.ItemBean;
import com.t.core.entities.Item;
import com.t.service.interfaces.IItemService;
import com.t.service.interfaces.IMerchantService;
import com.t.service.interfaces.ITagService;
import com.t.utils.BaseAction;
import com.t.utils.ImageService;


public class ItemAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private String[] merchantIds;//多选商家列表
	private String merchantId;//单个商家

	private String itemId;
	private String itemName;
	private String itemDescription;
	private String itemPrice ;
	private String itemTypeid;
	private ItemBean itemBean;
	private Double itemRate;
	private String itemTags;

	private File itemImage;

	private List<ItemBean> itemBeans;
	
	public List<ItemBean> getItemBeans() {
		return itemBeans;
	}
	public void setItemBeans(List<ItemBean> itemBeans) {
		this.itemBeans = itemBeans;
	}

	/*  */
	private File ItemImage;

	private String fileName; // 上传文件名
	private String oldPicture;

	private String imageFileName;
	private ImageService imageService;
	private List<Item> itemList;
	private Item item;
	private String select;

	@Autowired
	private IMerchantService mservice;

	@Autowired
	private IItemService itemServe;
	@Autowired
	private ITagService tagservice;//添加tag
	//android 客户端功能实现
	//根据商铺ID获取某商铺的全部商品
	public String getItemsByMerchantId(){
		itemBeans = new ArrayList<ItemBean>();
		itemBeans = itemServe.findMerchantItemsById(Integer.valueOf(merchantId),select);
		if(itemBeans.size() == 0){
			result.put(STATE,FAIL);
		}else{
			result.put("results",itemBeans);
			result.put(STATE,SUCCESS);
		}
		return SUCCESS;
	}
	//根据商品的Id获取商品的详情
	public String getItemByItemId(){
		itemBean = itemServe.getItemByItemId(Integer.valueOf(itemId));
		if(itemBean.getItemId() == null){
			result.put(STATE,FAIL);
			result.put("result", itemBean);
		}
		else{
		result.put("result",itemBean);
		result.put(STATE,SUCCESS);
		}
		return SUCCESS;
	}
	//web商家端功能实现
	// 转换图片名称
	public String getExtention(String fileName) {
		int pos = fileName.lastIndexOf(".");
		return new Date().getTime() + fileName.substring(pos);
	}

	// 添加商品
	@SuppressWarnings("static-access")
	public String itemAdd() {
	   // System.out.println(merchantIds[0]+"  "+merchantIds[1]);
		Item it = new Item();
		int newitemId;
		List<String> tags = new ArrayList<String>();
		File imageFile = null;

		try{	
			if(null!=fileName){
				imageFileName = getExtention(fileName) ;  //获得唯一的名称
				imageFile = new File(ServletActionContext.getServletContext().getRealPath("ItemUploadImages")+ "/" + imageFileName);   
				imageService.copy(itemImage, imageFile);  //把图片写入到上面设置的路径里  
			}else{
				imageFileName="000000.jpg";
			}		

			String temp[] = itemTags.split(";");
			for(int i=0;i<temp.length;i++){
				tags.add(temp[i]);
			}

			//根据前台选中的商家集合，循环添加商品
			for(int i=0;i<merchantIds.length;i++){
			it.setDescription(itemDescription.trim());
			it.setItemName(itemName.trim());
			it.setMerchant(Integer.parseInt(merchantIds[i]));
			it.setPrice(Double.parseDouble(itemPrice.trim()));
			it.setType(Integer.parseInt(itemTypeid));
			it.setRate(0.0);//默认为0
			it.setCheckStatus("0");//默认为0
			it.setPicture(imageFileName);

			newitemId = itemServe.itemAdd(it);

			//将tags添加进对应的表中
			tagservice.insertTag(0,newitemId,(long)3,tags);
			}
			return "itemAddSuccess";
		}catch(Exception e){
			System.out.println(e);
			return "error";
		}
	}

	// 商品列表
	public String getList() {
		if(this.getMyshopids().size()!=0){
			this.itemList = itemServe.findMerchantItems(this.getMyshopids());
			if (itemList!= null) {
				ActionContext.getContext().put("itemlist", itemList);
				ActionContext.getContext().put("shoplist", this.getMymerchantlist());
				return "itemList";
			}
			return "emptyItem";
		}return "noshop";
	}


	//按id查看商品
	public String findItemById(){
		this.item = itemServe.findItemById(itemId);
		if (item != null) {
			ActionContext.getContext().put("item",item);
			ActionContext.getContext().put("shoplist", this.getMymerchantlist());
			return "itemFindSuccess";
		} else {
			return "itemNotExist";
		}	
	}

	public String queryItemById(){
		this.item = itemServe.findItemById(itemId);
		if (item != null) {
			ActionContext.getContext().put("itemlist",item);
			return "queryItemSuccess";
		} else {
			return "itemNotExist";
		}
	}

	public String queryItemByName(){
		if(this.getMyshopids()!=null){
			this.itemList = itemServe.queryItemdByName(itemName, this.getMyshopids());
			if (itemList != null) {
				ActionContext.getContext().put("itemlist",itemList);
				return "queryItemSuccess";
			} else {
				return "itemNotExist";
			}
		}else
			return "itemNotExist";
	}

	//删除商品
	public String deleteItemById(){
		try {
			itemServe.deleteItemById(Integer.parseInt(itemId));
			return "itemdeleteuccess";
		}catch (Exception e){
			return "itemdeletefailed";
		}		
	}

	//更新商品
	@SuppressWarnings("static-access")
	public String updateItem(){
		//System.out.println(itemId);
		File imageFile = null;
		try{

			if(null!=fileName){
				imageFileName = getExtention(fileName) ;  //获得唯一的名称
				imageFile = new File(ServletActionContext.getServletContext().getRealPath("ItemUploadImages")+ "/" + imageFileName);   
				imageService.copy(itemImage, imageFile);  //把图片写入到上面设置的路径里  
			}else{
				imageFileName=oldPicture;
			}		

			Item it = itemServe.findItemById(itemId);
			//it.setItemId(Integer.parseInt(itemId));
			it.setDescription(itemDescription);
			it.setItemName(itemName);
			//it.setMerchant(Integer.parseInt(merchantId));
			it.setPrice(Double.parseDouble(itemPrice));

			it.setType(Integer.parseInt(itemTypeid));
			it.setPicture(imageFileName);

			itemServe.updateItem(it) ;
			ActionContext.getContext().put("item",it);
			return "updatesuccess";
		}catch(Exception e){
			System.out.println(e);
			return "error";
		}
	}


	/*综合搜索*/
	public String IntegratedSearch(){
		try{
			merchantId += "-1";
			itemId += "-1";
			itemName += "-1";
			if(this.getMyshopids()!=null)
			  itemList = itemServe.integratedSearch(merchantId.trim(),itemId.trim(),itemName.trim(), this.getMyshopids());
			ActionContext.getContext().put("shoplist", this.getMymerchantlist());
			ActionContext.getContext().put("itemlist",itemList);
			return SUCCESS;
		}catch(Exception e){
			System.out.println(e);
			return FAIL;
		}

	}

	/*添加商品之前先获得商铺列表*/
	public  String ItemAddPre(){
		try{
			if( this.getMymerchantlist()!=null){
				
				ActionContext.getContext().put("shoplist", mservice.integratedSearch("-1","-1", "-1", this.getMyshopids()));
				return SUCCESS;
			}else
				return EMPTY;
		}catch(Exception e)
		{
			return FAIL;
		}
	}
	
	public String updateItempre(){
		try{		
			ActionContext.getContext().put("shoplist", this.getMymerchantlist());
			return SUCCESS;
		}catch(Exception e)
		{
			return FAIL;
		}	
	}

	/*各种getter setter*/
	public Double getItemRate() {
		return itemRate;
	}

	public void setItemRate(Double itemRate) {
		this.itemRate = itemRate;
	}

	public String getOldPicture() {
		return oldPicture;
	}

	public void setOldPicture(String oldPicture) {
		this.oldPicture = oldPicture;
	}

	public String getItemId() {
		return itemId;
	}

	public String getItemTags() {
		return itemTags;
	}

	public void setItemTags(String itemTags) {
		this.itemTags = itemTags;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
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

	public String getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(String itemPrice) {
		this.itemPrice = itemPrice;
	}

	public String getItemTypeid() {
		return itemTypeid;
	}

	public void setItemTypeid(String itemTypeid) {
		this.itemTypeid = itemTypeid;
	}

	public String getFileName() {
		return fileName;
	}


	public void setItemImageFileName(String fileName) {
		this.fileName = fileName;
	}


	public File getItemImage() {
		return itemImage;
	}

	public void setItemImage(File itemImage) {
		this.itemImage = itemImage;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public ImageService getImageService() {
		return imageService;
	}

	public void setImageService(ImageService imageService) {
		this.imageService = imageService;
	}

	public IItemService getItemServe() {
		return itemServe;
	}


	public void setItemServe(IItemService itemServe) {
		this.itemServe = itemServe;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String[] getMerchantIds() {
		return merchantIds;
	}
	public void setMerchantIds(String[] merchantIds) {
		this.merchantIds = merchantIds;
	}
	public ItemBean getItemBean() {
		return itemBean;
	}
	public void setItemBean(ItemBean itemBean) {
		this.itemBean = itemBean;
	}
	public List<Item> getItemList() {
		return itemList;
	}
	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public String getSelect() {
		return select;
	}
	public void setSelect(String select) {
		this.select = select;
	}
	
	
}
