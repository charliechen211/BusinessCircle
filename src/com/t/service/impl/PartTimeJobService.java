package com.t.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.t.bean.PartTimeJobBean;
import com.t.core.dao.PartTimeJobDao;
import com.t.core.dao.UserDao;
import com.t.core.entities.PartTimeJob;
import com.t.service.interfaces.IPartTimeJobService;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class PartTimeJobService implements IPartTimeJobService{

	@Autowired
	public PartTimeJobDao ptDao;
	@Autowired
	private UserDao userDao;

	public void add(PartTimeJob job) {
		ptDao.saveOrUpdate(job);   
	}
	/*public PartTimeJobDetailBean getDetails(int jobId) {
		PartTimeJobDetailBean bean = new PartTimeJobDetailBean();
		//User user = new User();
		PartTimeJob job = ptDao.findUniqueByProperty("jobId", jobId);
		bean.setContent(job.getContent());
		//user = userDao.findUniqueByProperty("userId", job.getUserId());
		//bean.setUserName(user.getUserName());

		return bean;
	}*/

	@SuppressWarnings("unchecked")
	public List<PartTimeJobBean> getList(Integer jobtype,Integer pageId,Integer pageSize) {
		List<PartTimeJob> temp = new ArrayList<PartTimeJob>();
		List<PartTimeJobBean> result = new ArrayList<PartTimeJobBean>();

		Query q = ptDao.createQuery("from PartTimeJob e where e.jobtype =:jobtype order by date desc");
		q.setParameter("jobtype", jobtype);//兼职或者实习
		q.setFirstResult(pageId*pageSize);
		q.setMaxResults(pageSize);
		try{
		temp = q.list();
		}catch (Exception e){
			System.out.println("***************************"+e);
		}
		
		if(temp != null){
			for(PartTimeJob ele:temp){
				PartTimeJobBean ptbean = new PartTimeJobBean();
				String time = String.valueOf(ele.getDate());
				ptbean.setJobId(ele.getJobId());
				ptbean.setTitle(ele.getTitle());
				ptbean.setCommentNum(ele.getCommentNum());
				ptbean.setDate(time.substring(0,time.length()-10));
				result.add(ptbean);
			}
			return result;
		}else
			return null;
	}

	@Override
	public PartTimeJob getDetails(int jobId) {
		return  ptDao.findUniqueByProperty("jobId", jobId);
	}

}
