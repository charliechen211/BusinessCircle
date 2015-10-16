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
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Autowired
	private IRecommenderService recService;
	
	// 从实时推荐系统中获取推荐商家
	public String recMerchant() {		
		try {
			merchantBean = recService.fetchRecMerchantBeans(userId);
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
