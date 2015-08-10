package com.t.service.interfaces;

import java.util.List;

import com.t.bean.ModuleObjectBean;
import com.t.bean.RecommendFriendBean;
import com.t.bean.SchoolsRegionsBean;
import com.t.bean.UserBean;

public interface IUserService {
	
	//我的大学 注册用户
	public Integer register(String mobilePhone,String password,Integer sex,Integer age,   //必选项
			String nickname, /*Integer job,Integer hometown,Integer income,Integer point,*/int schoolId, int regionId,String picture,String tagContent);    //可选项  
	
	//智慧商圈注册用户
	public Integer bcregister(String mobilePhone,String password,Integer sex,Integer age,   //必选项
			String nickname, Integer job,Integer hometown,Integer income,Integer point,String picture);    //可选项  
	
	public Integer login(String mobilePhone, String password);
	//提取用户的详细信息
	public UserBean fetchUserBean(Integer userId);
	public List<UserBean> fetchFriends(Integer userId);
	public List<UserBean> getMyFans(Integer userId);
	//管理用户的好友
	public Integer addFriend(Integer userId,Integer friendId);
	public Integer deleteFriend(Integer userId,Integer friendId);
	//推荐好友
	public List<RecommendFriendBean> getRecommendFriends(Integer userId);
	
	//获得标签库
	public List<String> getTagLib();
	
	//获得自定义个性标签
	public List<String> getMyTags(Integer userId);
	
	public void addTag(Integer userId/*,Integer tagId*/,String tagContent);
	
	//获得学校和校区
	public List<SchoolsRegionsBean> getAllSchoolsAndRegions();
	
	//修改个人信息
	public void modifyInfo (int userId,String picture,String nickName,int schoolId,int regionId,String tags);   
	
	//获得推荐信息
	public List<ModuleObjectBean> recommendInfos();
	
	//更新积分
	public void updateScore(int userId,int score);
	
}
