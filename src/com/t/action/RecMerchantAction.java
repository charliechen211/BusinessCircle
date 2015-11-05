package com.t.action;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;

import com.t.bean.MerchantBean;
import com.t.core.entities.Merchant;
import com.t.core.entities.TagEntity;
import com.t.service.interfaces.IRecommenderService;
import com.t.utils.BaseAction;

public class RecMerchantAction extends BaseAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<MerchantBean> merchantBean;

	private Integer userId;
	
	private Double longitude;
	
	private Double latitude;
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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


	@Autowired
	private IRecommenderService recService;
	
	// 从实时推荐系统中获取综合推荐商家
	public String recMerchant() {		
		try {
			merchantBean = recService.fetchRecMerchantBeans(userId, longitude, latitude);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.put("STATE", FAIL);
			return FAIL;
		}
		result.put("result", merchantBean);
		result.put(STATE, SUCCESS);
		return SUCCESS;
	}
	
	// 从实时推荐系统中获取最热商家
	public String hotMerchant() {		
		try {
			merchantBean = recService.fetchHotMerchantBeans(userId, longitude, latitude);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.put("STATE", FAIL);
			return FAIL;
		}
		result.put("result", merchantBean);
		result.put(STATE, SUCCESS);
		return SUCCESS;
	}
	
	// 从实时推荐系统中获取个性推荐商家
	public String cfRecMerchant() {		
		try {
			merchantBean = recService.fetchCfRecMerchantBeans(userId, longitude, latitude);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.put("STATE", FAIL);
			return FAIL;
		}
		result.put("result", merchantBean);
		result.put(STATE, SUCCESS);
		return SUCCESS;
	}
	
}
