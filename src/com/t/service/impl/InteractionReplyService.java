package com.t.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.t.bean.PartTimeJobBean;
import com.t.core.dao.InteractionDao;
import com.t.core.dao.InteractionReplyDao;
import com.t.core.entities.InterReplyBean;
import com.t.core.entities.Interaction;
import com.t.core.entities.InteractionReply;
import com.t.core.entities.PartTimeJob;
import com.t.service.interfaces.IInteractionReplyService;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class InteractionReplyService implements IInteractionReplyService{
	@Autowired
    public InteractionReplyDao IRDao;
	@Autowired
	public InteractionDao InterDao;
	
	public int addReply(InteractionReply ele) {   //添加一条回复
		 Interaction inter = InterDao.findUniqueByProperty("id", ele.getTopicId());
		 inter.setReplyNum(inter.getReplyNum()+1);
		 InterDao.saveOrUpdate(inter);   //回复时先将帖子的回复数增 1
	     return IRDao.save(ele);	
	}

	@SuppressWarnings("unchecked")
	public List<InterReplyBean> getReplies(int topicId/*,Integer pageId,Integer pageSize*/) {  //获得某个帖子的所有回复
		List<InteractionReply> temp = new ArrayList<InteractionReply>();
		List<InterReplyBean> result = new ArrayList<InterReplyBean>();
		Query q = IRDao.createQuery("from InteractionReply e where e.topicId =:id");
		q.setParameter("id", topicId);
		/*q.setFirstResult(pageId*pageSize);
		q.setMaxResults(pageSize);*/
		temp = q.list();
	
		if(temp != null){
			for(InteractionReply ele:temp){
				InterReplyBean bean = new InterReplyBean();
				String time = String.valueOf(ele.getDate());
				bean.setContent(ele.getContent());
				bean.setDate(time.substring(0,time.length()-2));
				result.add(bean);
			}
			return result;
		}else
			return null;
	}

}
