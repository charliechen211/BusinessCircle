package com.t.core.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.t.core.entities.UserFriend;
import com.t.utils.BaseDao;
@Repository
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserFriendDao extends BaseDao<UserFriend,Integer> {
	public UserFriendDao(){
		this.entityClass = UserFriend.class;
	}
	public Integer getFanNum(Integer userId){
		return this.findByProperty("friendId", userId).size();
	}
	public Integer getFollowNum(Integer userId){
		return this.findByProperty("userId", userId).size();
	}

}
