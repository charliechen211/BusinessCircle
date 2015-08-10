package com.t.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.t.bean.SchoolNewsBean;
import com.t.core.dao.SchoolNewsDao;
import com.t.core.entities.SchoolNews;
import com.t.service.interfaces.ISchoolnewsService;


@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class SchoolNewsService implements  ISchoolnewsService {
	@Autowired
	public SchoolNewsDao snDao;

	/**
	 * 获得新闻详情
	 */
	public String getContent(int topicId) {
		SchoolNews news = snDao.findUniqueByProperty("topicId",topicId);
		return news.getContent();
	}

	/**
	 * 获得新闻列表
	 */
	@SuppressWarnings("unchecked")
	public List<SchoolNewsBean> getList(int topicType,int schoolId,Integer pageId,Integer pageSize) {
		List<SchoolNewsBean> result = new ArrayList<SchoolNewsBean>();
		List<SchoolNews> temp = new ArrayList<SchoolNews>();
		if(temp != null){
			/*if(flag==1 || flag==2){  //1是新闻 2是学术  此外为全部*/
				Query q = snDao.createQuery("from SchoolNews e where e.topicType =:type and e.schoolId=:schoolId");
				q.setInteger("type",topicType);
				q.setInteger("schoolId",schoolId);
				q.setFirstResult(pageId*pageSize);
				q.setMaxResults(pageSize);
				temp = q.list();
				for(SchoolNews ele:temp){
					SchoolNewsBean snbean = new SchoolNewsBean();
					String time = String.valueOf(ele.getDate());
					time = time.substring(0,9);
					time = time.replaceFirst("-", "年");
					time = time.replaceFirst("-", "月");
					snbean.setTitle(ele.getTitle());
					snbean.setTopicId(ele.getTopicId());
					snbean.setTimestamp(time+"日");
					result.add(snbean);
				}
			
			return result;
		}else
			return null;
	}
}
