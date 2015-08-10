package com.t.service.interfaces;

import java.util.List;
import com.t.core.entities.SchoolAroundItem;

public interface ISubscriptionService {
	public Integer manageBCSubscription(Integer userId,Integer merchantId,Integer type);  //管理用户订阅--用于智慧商圈
	
	/*以下是用于环校通的函数*/
	public void manageSubscription(Integer userId,Integer itemId,Integer delFlag);   //管理用户订阅
	
	public List<SchoolAroundItem> getFollowList(Integer userId,Integer pageId,Integer pageSize);   //获取用户订阅列表
}
