package com.t.service.interfaces;

import java.util.List;

import com.t.bean.AdvertiseBean;
import com.t.core.entities.Advertise;

public interface IAdvertiseService {
	//android 根据用户的id拉取其关注的商铺的广告
	public List<AdvertiseBean> getMyAdvertises(Integer userId);
	
    public int advertiseAdd(Advertise ad);
	
	//获得登录用户广告列表
	public List<Advertise> findMyAds(int merchantOwnerId);
	
	//查找ad用于查看详情
	public Advertise findAdById(int adid);
	
	public void deleteadById(int adid);

	//更新数据
	public void updateAdvertise(Advertise ad);
	
	/*广告综合查询*/
	public List<Advertise> integratedSearch(String adverId,String merchantId,int merchantOwnerId);
}
