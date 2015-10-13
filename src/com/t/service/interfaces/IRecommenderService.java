package com.t.service.interfaces;

import java.util.List;

import com.t.bean.MerchantBean;

public interface IRecommenderService {
	
	public List<MerchantBean> fetchRecMerchantBeans(int userId);
	
}
