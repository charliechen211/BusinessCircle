package com.t.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.t.core.dao.ModuleTabDao;
import com.t.core.dao.PartTimeJobDao;
import com.t.core.dao.SchoolActivityDao;
import com.t.core.dao.SchoolAroundItemDao;
import com.t.core.dao.SchoolNewsDao;
import com.t.core.dao.ShInfoDao;

import com.t.core.entities.PartTimeJob;
import com.t.core.entities.SchoolActivity;
import com.t.core.entities.SchoolAroundItem;
import com.t.core.entities.SchoolNews;
import com.t.core.entities.ShLVInfo;
import com.t.service.interfaces.IModuleTabService;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ModuleTabService implements IModuleTabService{
	
	@Autowired
	private ModuleTabDao tabDao;
	
	//各大版块的Dao
	@Autowired
	public SchoolAroundItemDao schoolAroundItemDao;
	@Autowired
	public SchoolNewsDao schoolnewsDao; 
	@Autowired 
	public PartTimeJobDao partTimeJobDao;
	@Autowired
	public ShInfoDao shlvDao;
	@Autowired
	public SchoolActivityDao activityDao;

	public Object getDatail(Integer moduleId, Integer itemId) {
		//校园周边的记录
		if(moduleId == 1){
			SchoolAroundItem saitem = schoolAroundItemDao.findUniqueByProperty("itemId",itemId);  
			return saitem; 
		}

		//校园资讯
		if(moduleId == 2){
			SchoolNews news = schoolnewsDao.findUniqueByProperty("topicId",itemId);  
			return news;
		}

		//兼职招聘
		if (moduleId == 3){
			PartTimeJob ptj = partTimeJobDao.findUniqueByProperty("jobId",itemId);
			return ptj;
		}

		//二手或者鹊桥
		if (moduleId == 4 || moduleId == 5){
			ShLVInfo slv = shlvDao.findUniqueByProperty("shId",itemId);
			return  slv;
		}

		//互动墙不能收藏
		
		//校园活动
		if (moduleId == 7){
			SchoolActivity activity = activityDao.findUniqueByProperty("activityId",itemId);
			return activity;
		}	
		return null;
		 
	}
	
	 
}
