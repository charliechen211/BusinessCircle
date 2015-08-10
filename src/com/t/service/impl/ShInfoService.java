package com.t.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.t.bean.ShInfoBean;
import com.t.core.dao.ModuleCommentDao;
import com.t.core.dao.ShGoodDao;
import com.t.core.dao.ShInfoDao;
import com.t.core.dao.UserInfoDao;
import com.t.core.entities.ShLVInfo;
import com.t.core.entities.UserInfo;
import com.t.service.interfaces.IShInfoService;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ShInfoService implements IShInfoService{

	@Autowired
	private ModuleCommentDao shCommentDao;
	@Autowired
	private ShGoodDao shGoodDao;
	@Autowired
	private ShInfoDao shInfoDao;
	@Autowired
	private UserInfoDao userInfoDao;
	//拉取商品或商铺的评论信息
	public List<ShInfoBean> fetchInfos(Integer circleId, Integer type, Integer pageId, Integer pageSize){
		/*List<ShInfoBean> shInfoBeans = new ArrayList<ShInfoBean>();
		List<ShLVInfo> shInfos = new ArrayList<ShLVInfo>();
		shInfos = shInfoDao.getInfos(circleId, type, pageId, pageSize);
		for (ShLVInfo info : shInfos) {
			ShInfoBean shInfoBean = new ShInfoBean(info);
			UserInfo userInfo = new UserInfo();
			if(userInfoDao.findByProperty("userId", info.getUserId()).size() > 0)
				userInfo = userInfoDao.findByProperty("userId", info.getUserId()).get(0);
			shInfoBean.setUserName(userInfo.getNickname());
			shInfoBean.setPicture(userInfo.getPicture());
			int replyNum;
			List<ModuleComment> replylist = shCommentDao.findByCriteria(Restrictions.eq("shId", info.getShId()),Restrictions.eq("type", type),Restrictions.eq("delFlg", 0));			
			if(replylist != null)
				replyNum = replylist.size();
			else
				replyNum = 0;
			shInfoBean.setReplyNum(replyNum);

		    int goodNum;
			List<ShGood> goodlist = shGoodDao.findByCriteria(Restrictions.eq("shId", info.getShId()),Restrictions.eq("good", 1),Restrictions.eq("type", type));			
			if(goodlist != null)
				goodNum = goodlist.size();
			else
				goodNum = 0;
			shInfoBean.setGoodNum(goodNum);

			int myGood;
			goodlist = shGoodDao.findByCriteria(Restrictions.eq("shId", info.getShId()),Restrictions.eq("type", type),Restrictions.eq("good", 1),Restrictions.eq("userId", info.getUserId()));			
			if(goodlist != null && goodlist.size() > 0)
				myGood = 1;
			else
				myGood = 0;
			shInfoBean.setMyGood(myGood);  

			shInfoBeans.add(shInfoBean);
		}*/
		List<ShLVInfo> temp = new ArrayList<ShLVInfo>();
		List<ShInfoBean> result = new ArrayList<ShInfoBean>();

		temp = shInfoDao.getInfos(circleId, type, pageId, pageSize);

		if(temp != null){
			for(ShLVInfo ele:temp){
				ShInfoBean shbean = new ShInfoBean();
				shbean.setShlvinfo(ele);

				String time = String.valueOf(ele.getTimestamp());		 
				shbean.setTimestamp(time.substring(0,time.length()-2));

				UserInfo userInfo = new UserInfo();
				if(userInfoDao.findByProperty("userId", ele.getUserId()).size() > 0)
					userInfo = userInfoDao.findByProperty("userId", ele.getUserId()).get(0);
				shbean.setUserName(userInfo.getNickname());
				shbean.setUserPic(userInfo.getPicture());

				result.add(shbean);
			}
			return result;
		}else
			return null; 
	}

	public Integer addInfo(Integer userId, Integer circleId, String title, String description, Integer type,String linkWay,String picture) {
		ShLVInfo shInfo = new ShLVInfo(null, userId, circleId, title, description, null, type, 0,linkWay,picture);
		return shInfoDao.save(shInfo);
	}

	@Override
	public ShLVInfo getInfo(int id) {
		return shInfoDao.findUniqueByProperty("shId", id); 
	}

}
