package com.t.service.interfaces;

import java.util.List;

import org.json.JSONException;

import com.t.bean.MerchantBean;

public interface IRecommenderService {
	
//	public List<MerchantBean> fetchRecMerchantBeans(int userId) throws JSONException;	

//	public List<MerchantBean> fetchHotMerchantBeans(int userId) throws JSONException;
	
	public List<MerchantBean> fetchRecMerchantBeans(int userId, double longitude, double latitude) throws JSONException;	

	public List<MerchantBean> fetchHotMerchantBeans(int userId, double longitude, double latitude) throws JSONException;
	
}
