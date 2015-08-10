package com.t.service.interfaces;

import java.util.List;

import com.t.bean.SchoolNewsBean;

public interface ISchoolnewsService {
	public String getContent(int topicId) ;
	public List<SchoolNewsBean> getList(int flag,int schoolId,Integer pageId,Integer pageSize);
}
