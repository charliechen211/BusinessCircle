package com.t.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.t.bean.BadInteractionsBean;
import com.t.bean.InteractionBean;
import com.t.core.dao.InteractionDao;
import com.t.core.dao.UserDao;
import com.t.core.dao.UserFriendDao;
import com.t.core.entities.Interaction;
import com.t.core.entities.User;
import com.t.core.entities.UserFriend;
import com.t.service.interfaces.IInteractionService;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class InteractionService implements IInteractionService{

	@Autowired
	public InteractionDao InterDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private UserFriendDao ufDao;

	public int addInteraction(Interaction ele) {  //添加一个帖子
		return InterDao.save(ele);
	}


	public void delInteractionById(int id) {    
		InterDao.delete(id);
	}

	@SuppressWarnings("unchecked")
	public List<InteractionBean> getList(int pageId, int pageSize,int schoolId,int userId) {  //获得帖子列表
		List<Interaction> temp = new ArrayList<Interaction>();
		List<InteractionBean> result = new ArrayList<InteractionBean>();
		List<UserFriend> ufriends = new ArrayList<UserFriend>();		
		List<Integer> friendIds = new ArrayList<Integer>();//好友id集合
		User user = new User();
		
		Query q1 = ufDao.createQuery("from UserFriend e where e.userId =:id");
		q1.setInteger("id", userId);
		ufriends = q1.list();
		for(UserFriend ele:ufriends){
			friendIds.add(ele.getFriendId());
		}
		System.out.println("********* "+friendIds.size());
		
		Query q2 = InterDao.createQuery("from Interaction e where e.schoolId =:id order by date desc");
		q2.setInteger("id",schoolId);
		q2.setFirstResult(pageId*pageSize);
		q2.setMaxResults(pageSize);
		temp = q2.list(); 
		System.out.println("******************** "+temp.size());
		if(temp != null){
			for(Interaction ele:temp){
				InteractionBean onerecord = new InteractionBean();
				String time = String.valueOf(ele.getDate());
                
				onerecord.setContent(ele.getContent());
				onerecord.setCommentNum(ele.getReplyNum());
				onerecord.setTopicId(ele.getId());
				onerecord.setTimestamp(time.substring(0,time.length()-2));
				if(friendIds.contains(ele.getUserId()) || ele.getUserId() == userId)
				    onerecord.setFriend(true);
				else
					onerecord.setFriend(false);
			
				result.add(onerecord);
			}
			return result;
		}else
			return null;
	}

	public void reportBad(int topicId) {
		Interaction inter = InterDao.findUniqueByProperty("id",topicId);
		inter.setAlarmNum(inter.getAlarmNum()+1);
		InterDao.saveOrUpdate(inter);   //举报数增 1	
	}

	@SuppressWarnings("unchecked")
	public List<BadInteractionsBean> getBadNews() {  //超过3次就认为该审核了
		List<Interaction> badnews = new ArrayList<Interaction>();
		List<BadInteractionsBean> badBeanslist = new ArrayList<BadInteractionsBean>();
		User user = new User();

		Query q = InterDao.createQuery("from Interaction e where e.alarmNum >=3 order by date desc");
		badnews = q.list();  //先找到举报次数超过3次的帖子
		System.out.println("*********************************************"+badnews.size());
		if(badnews != null){
			for(Interaction ele:badnews){
				BadInteractionsBean badInter = new BadInteractionsBean();
				user = userDao.findUniqueByProperty("userId", ele.getUserId());
				String time = String.valueOf(ele.getDate());

				badInter.setTopicId(ele.getId());
				badInter.setUserName(user.getUserName());
				badInter.setContent(ele.getContent());
				badInter.setAlarmNum(ele.getAlarmNum());
				badInter.setTime(time.substring(0,time.length()-2));

				badBeanslist.add(badInter);
			}
			return badBeanslist;
		}else
			return null;
	}

	public void passInteraction(int topicId) {
		Interaction inter = InterDao.findUniqueByProperty("id",topicId);
		inter.setAlarmNum(0);
		InterDao.saveOrUpdate(inter);   //举报数置为零	
	}

}
