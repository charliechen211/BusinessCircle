package com.t.service.interfaces;

import java.util.List;

import com.t.bean.ShInfoBean;
import com.t.core.entities.ShLVInfo;

public interface IShInfoService {
	
	//获得二手或者鹊桥列表
	public List<ShInfoBean> fetchInfos(Integer circleId, Integer type, Integer pageId, Integer pageSize);
	
	//添加一条记录
	public Integer addInfo(Integer userId, Integer circleId, String title, String description, Integer type,String linkWay,String Picture);
    
	//获得某个帖子
	public ShLVInfo getInfo(int id);
}