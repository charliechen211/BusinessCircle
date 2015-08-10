package com.t.bean;

/**
 * 安卓端---首页的“推荐列表”和“收藏列表”收到的数据结构
 * @author WangZhaoLi
 *
 */
public class ModuleObjectBean {
    public int moduleId; //属于哪个模块
    public String moduleName;
    public int itemId;//具体的记录Id
    
    public String title;
    public boolean adverFlag;//是否有优惠信息
    public int adverId;
    public String content;
    public String picture;
    public String date;  //消息发布时间
  //经纬度
  	private Double longitude;
  	private Double latitude;
  	private String itemLocation;
  	private String itemTel;
    
	public int getModuleId() {
		return moduleId;
	}
	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}

	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getAdverId() {
		return adverId;
	}
	public void setAdverId(int adverId) {
		this.adverId = adverId;
	}
	public boolean isAdverFlag() {
		return adverFlag;
	}
	public void setAdverFlag(boolean adverFlag) {
		this.adverFlag = adverFlag;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
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
    
    
    
}
