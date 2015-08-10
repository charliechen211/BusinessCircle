package com.t.action;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.t.bean.ShInfoBean;
import com.t.service.interfaces.IShInfoService;
import com.t.utils.BaseAction;

public class ShLVInfoAction extends BaseAction {

	/**
	 * 鹊桥 二手板块
	 */
	private static final long serialVersionUID = 1L;
	private int shId;
	private Integer userId;
	private Integer circleId;
	private String title;
	private String description;
	private String timestamp;
	private Integer pageId;
	private Integer pageSize;
	private Integer type;

	private String linkWay;
	private String picture;

	private List<ShInfoBean> Infos;

	@Autowired
	private IShInfoService InfoService;

	//获取二手或者鹊桥信息列表
	public String fetchInfos(){
		try{
			Infos = InfoService.fetchInfos(circleId, type, pageId, pageSize);
		}catch(NullPointerException e){
			result.put(STATE, FAIL);
			return SUCCESS;
		}
		result.put("results", Infos);
		result.put(STATE,SUCCESS);
		return SUCCESS;
	}

	//提交二手-鹊桥信息
	public String addInfo() throws UnsupportedEncodingException
	{	
		/**
		 * 此处要加上上传图片的逻辑
		 */
		String fileName =String.valueOf(new Date().getTime())+".jpg";
		File dir = new File(ServletActionContext.getServletContext().getRealPath("AppUploadImages"));
		File file = new File(dir, fileName);
		try {
			FileOutputStream out = new FileOutputStream(file);

			ByteArrayInputStream in = new ByteArrayInputStream(picture.getBytes("ISO-8859-1"));

			byte[] buffer = new byte[1024 * 1024];
			int to_write = in.read(buffer);
			while (to_write > 0) {
				out.write(buffer, 0, to_write);
				to_write = in.read(buffer);
			}
			out.flush();
			out.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}

		InfoService.addInfo(userId, circleId, title, description, type,linkWay,fileName);
		result.put(STATE, SUCCESS);
		return SUCCESS;
	}

	public String getInfo(){
		try{
			result.put("result",InfoService.getInfo(shId));
			return SUCCESS;
		}catch (Exception e){
			return FAIL;
		}
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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getLinkWay() {
		return linkWay;
	}

	public void setLinkWay(String linkWay) {
		this.linkWay = linkWay;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public int getShId() {
		return shId;
	}

	public void setShId(int shId) {
		this.shId = shId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getCircleId() {
		return circleId;
	}

	public void setCircleId(Integer circleId) {
		this.circleId = circleId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}


}
