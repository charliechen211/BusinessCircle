package com.t.service.interfaces;

import java.util.List;

import com.t.bean.CircleDynamicBean;

public interface ICircleDynamicService {
	public int  addCircleDynamic(Integer userId,Integer entityId, Integer typeId, String content,Integer shareTo);
	public List<CircleDynamicBean> fetchCircleDynamic(Integer circleId);
	//查看我的分享
	public List<CircleDynamicBean> fetchMySharing(Integer userId);
	//删除我的分享
	public Boolean delMySharing(Integer shareId);
}
