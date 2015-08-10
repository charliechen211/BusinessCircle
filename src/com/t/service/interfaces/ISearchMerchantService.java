package com.t.service.interfaces;

import java.util.List;

import com.t.bean.MerchantBean;
import com.t.bean.UserBean;

public interface ISearchMerchantService {
	 public List<MerchantBean> searchMerchant(String Content,Integer pageId,Integer pageSize,Integer circleId,Integer typeId);
	 public List<MerchantBean> searchBasedLocation(Double longitude,Double latitude,Double distance,Integer pageId,Integer pageSize);
	 public List<UserBean> searchUser(String content,Integer pageId,Integer pageSize);
}
