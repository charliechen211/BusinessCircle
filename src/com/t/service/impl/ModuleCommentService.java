package com.t.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.t.bean.ModuleCommentBean;
import com.t.core.dao.ModuleCommentDao;
import com.t.core.dao.PartTimeJobDao;
import com.t.core.dao.SchoolActivityDao;
import com.t.core.dao.ShGoodDao;
import com.t.core.dao.ShInfoDao;
import com.t.core.dao.UserInfoDao;
import com.t.core.entities.ModuleComment;
import com.t.core.entities.PartTimeJob;
import com.t.core.entities.SchoolActivity;
import com.t.core.entities.ShGood;
import com.t.core.entities.ShLVInfo;
import com.t.core.entities.UserInfo;
import com.t.service.interfaces.IModuleCommentService;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ModuleCommentService implements IModuleCommentService{

	@Autowired
	private ModuleCommentDao shCommentDao;
	
	@Autowired
	public PartTimeJobDao ptDao;//兼职
	@Autowired
	private ShInfoDao shInfoDao;//二手 鹊桥
	
	@Autowired
	private ShGoodDao shGoodDao;
	@Autowired
	private UserInfoDao userInfoDao;
   
	@Autowired
    private SchoolActivityDao sacDao;
	
    /**
	 * 获得某个模块（type）中某个帖子（objectId）的所有评论
	 */
	public List<ModuleCommentBean> fetchComments(Integer objectId, Integer type) {
		List<ModuleCommentBean> shCommentBeans = new ArrayList<ModuleCommentBean>();
		List<ModuleComment> shComments = new ArrayList<ModuleComment>();
		shComments = shCommentDao.getCommentsByobjectId(objectId, type);
		for (ModuleComment comment : shComments) {
			ModuleCommentBean shCommentBean = new ModuleCommentBean(comment);
			UserInfo userInfo = new UserInfo();
			if(userInfoDao.findByProperty("userId", comment.getUserId()).size() > 0)
				userInfo = userInfoDao.findByProperty("userId", comment.getUserId()).get(0);
			shCommentBean.setUserName(userInfo.getNickname());
			shCommentBean.setPicture(userInfo.getPicture());
			shCommentBeans.add(shCommentBean);
		}
		return shCommentBeans;
	}

	/**
	 * 添加评论，每天添加一条就将评论数加一
	 */
	public Integer addComment(Integer userId, Integer objectId, String comment, Integer type) {
		ModuleComment Comment = new ModuleComment(null, userId, objectId, comment, null, type, 0);

		//根据不同的模块，将该帖子的评论数增1
		if(type ==1 || type==2 ){  //二手or 鹊桥
			ShLVInfo sl = shInfoDao.findByCriteria(Restrictions.eq("shId", objectId)).get(0);
			sl.setCommentNum(sl.getCommentNum()+1);
			this.shInfoDao.saveOrUpdate(sl);
		}
		
		if(type ==3){  //兼职招聘
            PartTimeJob ptj = ptDao.findByCriteria(Restrictions.eq("jobId", objectId)).get(0);
            ptj.setCommentNum(ptj.getCommentNum()+1);
            this.ptDao.saveOrUpdate(ptj);
		}

		if(type ==4){  //活动
			SchoolActivity sac = sacDao.findByCriteria(Restrictions.eq("activityId", objectId)).get(0);   
			sac.setCommentNum(sac.getCommentNum()+1);
			this.sacDao.saveOrUpdate(sac);
		}
		return shCommentDao.save(Comment);
	}	

	/**
	 * 点赞的业务逻辑
	 */
	public Integer setGood(Integer userId, Integer objectId, Integer type) {
		List<ShGood> list = shGoodDao.findByCriteria(Restrictions.eq("userId", userId),Restrictions.eq("shId", objectId),Restrictions.eq("type", type));
		if(list != null && list.size() > 0) {
			ShGood shGood = list.get(0);
			int good = shGood.getGood();
			good = 1- good;
			shGood.setGood(good);
			shGoodDao.saveOrUpdate(shGood);
			return good;
		}
		else {
			ShGood shGood = new ShGood(null, userId, objectId, 1, type);
			shGoodDao.save(shGood);
			return 1;
		}			
	}

}
