package com.t.service.interfaces;

import java.util.List;

import com.t.bean.SchoolActivityBean;
import com.t.core.entities.SchoolActivity;

public interface ISchoolActivityService {
	public SchoolActivity getActivity(int schactivityId);
	public List<SchoolActivityBean> getList(Integer pageId,Integer pageSize);
	public void activitypublish(String title,String content,String access,String picture,int userId);
}
