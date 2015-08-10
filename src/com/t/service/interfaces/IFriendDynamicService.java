package com.t.service.interfaces;

import java.util.List;

import com.t.bean.CircleDynamicBean;
import com.t.bean.FriendDynamicBean;

public interface IFriendDynamicService {
	public List<CircleDynamicBean> fetchFriendDynamic(Integer userId);
}
