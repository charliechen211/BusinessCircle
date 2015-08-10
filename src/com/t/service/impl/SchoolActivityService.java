package com.t.service.impl;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.t.service.interfaces.ISchoolActivityService;
import com.t.bean.SchoolActivityBean;
import com.t.core.dao.SchoolActivityDao;
import com.t.core.entities.SchoolActivity;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class SchoolActivityService implements ISchoolActivityService{
	
	@Autowired
	private SchoolActivityDao schactDao;
	
	//获得校园活动列表，幼稚!!!
//	@SuppressWarnings("unchecked")
//	public List<SchoolActivity> findSchoolActivity(){
//		return schactDao.getSchoolActivity();
//	}
	
	//获得活动详情
	public SchoolActivity getActivity(int schactivityId) {
		SchoolActivity schact = schactDao.findUniqueByProperty("activityId",schactivityId);
		return schact;
	}
	
	//获得校园活动列表
	@SuppressWarnings("unchecked")
	public List<SchoolActivityBean> getList(Integer pageId,Integer pageSize) {
		List<SchoolActivityBean> result = new ArrayList<SchoolActivityBean>();
		List<SchoolActivity> temp = new ArrayList<SchoolActivity>();
		
			Query q = schactDao.createQuery("from SchoolActivity where 1=1");
			//q.setInteger("activityId",schactivityId);
			q.setFirstResult(pageId*pageSize);
			q.setMaxResults(pageSize);
			temp = q.list();
			if(temp != null){
				for(SchoolActivity ele:temp){
					SchoolActivityBean schactbean = new SchoolActivityBean();
					String time = String.valueOf(ele.getDate());
					schactbean.setDate(time.substring(0,time.length()-2));
					schactbean.setTitle(ele.getTitle());
					schactbean.setActivityId(ele.getActivityId());
					schactbean.setAccess(ele.getAccess());
					schactbean.setContent(ele.getContent());
					schactbean.setPicture(ele.getPicture());
					schactbean.setCommentNum(ele.getCommentNum());
					result.add(schactbean);
					}
				return result;
			}
			else 
				return null;
	}
	
	@SuppressWarnings("static-access")
	public void activitypublish(String title,String content,String access,String picture,int userId){
		SchoolActivity schact = new SchoolActivity();
		schact.setTitle(title);
		schact.setContent(content);
		schact.setAccess(access);
		schact.setUserId(userId);
	    //上传照片
			String fileName =String.valueOf(new Date().getTime());
		    File dir = new File(ServletActionContext.getServletContext().getRealPath("AppUploadImages"));
		    File file = new File(dir, fileName);
		    try {
				FileOutputStream out = new FileOutputStream(file);
				System.out.println(out.toString());
				ByteArrayInputStream in = new ByteArrayInputStream(picture.getBytes("ISO-8859-1"));
				System.out.println(picture);
				byte[] buffer = new byte[1024 * 1024];
				int to_write = in.read(buffer);
				while (to_write > 0) {
					out.write(buffer, 0, to_write);
					to_write = in.read(buffer);
				}
				out.flush();
				out.close();
				} 
		    catch (Exception e) {
				e.printStackTrace();
				}
		schact.setPicture(fileName);
		schactDao.save(schact);
		}

}
