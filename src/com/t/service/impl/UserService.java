package com.t.service.impl;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.ServletActionContext;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.t.bean.ModuleObjectBean;
import com.t.bean.RecommendFriendBean;
import com.t.bean.SchoolsRegionsBean;
import com.t.bean.UserBean;
import com.t.core.dao.AdvertiseDao;
import com.t.core.dao.PartTimeJobDao;
import com.t.core.dao.SchoolActivityDao;
import com.t.core.dao.SchoolAroundItemDao;
import com.t.core.dao.SchoolDao;
import com.t.core.dao.SchoolNewsDao;
import com.t.core.dao.SchoolRegionDao;
import com.t.core.dao.ShInfoDao;
import com.t.core.dao.TagEntityDao;
import com.t.core.dao.UserDao;
import com.t.core.dao.UserFriendDao;
import com.t.core.dao.UserInfoDao;
import com.t.core.dao.UserSetTagsDao;
import com.t.core.dao.UserTagLibDao;
import com.t.core.entities.Advertise;
import com.t.core.entities.Collection;
import com.t.core.entities.PartTimeJob;
import com.t.core.entities.SchoolActivity;
import com.t.core.entities.SchoolAroundItem;
import com.t.core.entities.SchoolNews;
import com.t.core.entities.ShLVInfo;
import com.t.core.entities.Tag;
import com.t.core.entities.TagEntity;
import com.t.core.entities.User;
import com.t.core.entities.UserFriend;
import com.t.core.entities.UserInfo;
import com.t.core.entities.UserSetTags;
import com.t.core.entities.UserTagLib;
import com.t.service.interfaces.IUserService;
import com.t.utils.BaseHttpClient;
import com.t.utils.Constants;
import com.t.utils.ImageService;
import com.t.utils.RecommenderUtils;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserService implements IUserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private UserInfoDao userInfoDao;
	@Autowired
	private UserFriendDao userFriendDao;
	@Autowired
	private TagEntityDao tagEntityDao;

	@Autowired
	private SchoolDao schoolDao;

	@Autowired
	private SchoolRegionDao regionDao;

	@Autowired
	private UserTagLibDao taglibDao;

	@Autowired
	private UserSetTagsDao settagDao;

	@Autowired
	public SchoolAroundItemDao schoolAroundItemDao;
	@Autowired
	public SchoolNewsDao schoolnewsDao; 
	@Autowired 
	public PartTimeJobDao partTimeJobDao;

	@Autowired
	public AdvertiseDao adverDao;

	@Autowired
	public ShInfoDao shlvDao;
	@Autowired
	public SchoolActivityDao activityDao;

	private ImageService imageService;

	public Integer login(String mobilePhone, String password){
		List<User> list = userDao.findByProperty("mobilePhone", mobilePhone);
		if(list.isEmpty())
			return -1;
		User user = list.get(0);
		if(password.equals(user.getPassword()))
			return user.getUserId();
		else
			return -1;
	}

	//我的大学注册
	@SuppressWarnings("static-access")
	public Integer register(String mobilePhone,String password,Integer sex,Integer age,   //必选项
			String nickname, /*Integer job,Integer hometown,Integer income,*/int schoolId, int regionId,String picture,String tagContent) throws JSONException{    //可选项
		User user = new User();
		if(userDao.findByProperty("mobilePhone", mobilePhone).size() > 0){
			return -1;
		}
		user.setMobilePhone(mobilePhone);
		user.setPassword(password);
		user.setUserName(mobilePhone);
		user.setRegDate(null);
		Integer userId = userDao.save(user);
		//上传头像
		String fileName =String.valueOf(new Date().getTime());
		File dir = new File(ServletActionContext.getServletContext().getRealPath("HeadImages"));
		File file = new File(dir, fileName);
		//   System.out.println(fileName+"  "+filePath);
		try {
			FileOutputStream out = new FileOutputStream(file);
			//System.out.println(out.toString());
			ByteArrayInputStream in = new ByteArrayInputStream(picture.getBytes("ISO-8859-1"));
			//System.out.println(picture);
			byte[] buffer = new byte[1024 * 1024];
			int to_write = in.read(buffer);
			while (to_write > 0) {
				out.write(buffer, 0, to_write);
				to_write = in.read(buffer);
			}
			out.flush();
			out.close();
			/*File src = new File(picture);
	    	File dst = new File(filePath);
	    	imageService.copy(src, dst);*/
		} catch (Exception e) {
			e.printStackTrace();
		}   
		UserInfo userInfo = new UserInfo();
		userInfo.setUserId(userId);
		userInfo.setNickname(nickname);
		userInfo.setAge(age);
		userInfo.setSchoolId(schoolId);
		userInfo.setRegionId(regionId);
		userInfo.setSex(sex);
		userInfo.setPicture(fileName);

		userInfoDao.save(userInfo);

		addTag(userId,tagContent);
		return userId;
	}

	//商圈注册
	@SuppressWarnings("static-access")
	public Integer bcregister(String mobilePhone,String password,Integer sex,Integer age,   //必选项
			String nickname, Integer job,Integer hometown,Integer income,Integer point,String picture){    //可选项
		User user = new User();
		if(userDao.findByProperty("mobilePhone", mobilePhone).size() > 0){
			return -1;
		}
		user.setMobilePhone(mobilePhone);
		user.setPassword(password);
		user.setUserName(mobilePhone);
		user.setRegDate(null);
		Integer userId = userDao.save(user);
		//上传头像
		String fileName =String.valueOf(new Date().getTime());
		File dir = new File(ServletActionContext.getServletContext().getRealPath("HeadImages"));
		File file = new File(dir, fileName);
		//   System.out.println(fileName+"  "+filePath);
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
			/*	    	File src = new File(picture);
	    	File dst = new File(filePath);
	    	imageService.copy(src, dst);*/
		} catch (Exception e) {
			e.printStackTrace();
		}   
		UserInfo userInfo = new UserInfo(userId,nickname,age,job,hometown,income,point,sex,fileName);
		userInfoDao.save(userInfo);
		return userId;
	}

	//拉取用户详细信息
	@SuppressWarnings("unchecked")
	public UserBean fetchUserBean(Integer userId){

		UserBean bean = new UserBean();
		Integer schoolId = null;
		Integer regionId = null;
		List<String> tags = new ArrayList<String>();

		UserInfo userInfo = new UserInfo();
		if(userInfoDao.findByProperty("userId", userId).size() > 0){
			userInfo = userInfoDao.findByProperty("userId", userId).get(0);
			schoolId = userInfo.getSchoolId();
			regionId = userInfo.getRegionId();
			System.out.println(schoolId+"*****"+regionId);
		}

		List<TagEntity> tagEntitys= new ArrayList<TagEntity>();
		tagEntitys = tagEntityDao.getUserTags(userId);
		for (TagEntity tagEntity : tagEntitys) {
			tags.add(tagEntity.getTagName());
		}

		bean.setUsertags(getMyTags(userId));

		bean.setUserId(userInfo.getUserId());
		bean.setPicture(userInfo.getPicture());
		bean.setUserName(userInfo.getNickname());
		bean.setPoint(userInfo.getPoint());
		bean.setFanNum(userFriendDao.findByProperty("userId", userId) == null?0 : userFriendDao.findByProperty("userId", userId).size());
		bean.setFollowNum(userFriendDao.findByProperty("friendId", userId) ==null? 0:userFriendDao.findByProperty("friendId", userId).size());
		bean.setAge(userInfo.getAge());
		bean.setHome(userInfo.getHometown());
		bean.setJob(userInfo.getJob());
		bean.setTags(tags);
	    bean.setUserScore(userInfo.getPoint()==null? 0:userInfo.getPoint() );

		if(schoolId != null && regionId != null){   //用户的学校和校区
			bean.setRegionName(regionDao.getSchoolRegion(schoolId, regionId).getRegionName()); 
			bean.setSchoolName(schoolDao.getSchool(schoolId).getSchoolName());
		}
		return bean;
	}

	//拉取用户好友列表信息
	public List<UserBean> fetchFriends(Integer userId){
		List<UserBean> friends = new ArrayList<UserBean>();
		List<UserFriend> list = new ArrayList<UserFriend>();
		list = userFriendDao.findByProperty("userId", userId);
		for (UserFriend userFriend : list) {
			UserBean friend = new UserBean();
			friend = fetchUserBean(userFriend.getFriendId());
			friends.add(friend);
		}
		return friends;
	}

	public Integer addFriend(Integer userId,Integer friendId){
		if(userId == friendId)
			return Constants.ERROR;
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", userId);
		paramMap.put("friendId", friendId);
		String sql = "select * from user_friend where uid= :userId and fid= :friendId";
		if(userFriendDao.findBySQL(sql, paramMap).size()>0)
			return Constants.ERROR;
		UserFriend userFriend = new UserFriend();
		userFriend.setUserId(userId);
		userFriend.setFriendId(friendId);
		return userFriendDao.save(userFriend);
	}

	public Integer deleteFriend(Integer userId,Integer friendId){
		if(userId == friendId)
			return Constants.ERROR;
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", userId);
		paramMap.put("friendId", friendId);
		String sql = "select * from user_friend where uid= :userId and fid= :friendId";
		UserFriend userFriend = new UserFriend();
		if(userFriendDao.findBySQL(sql, paramMap).size() > 0){
			userFriend = (UserFriend)userFriendDao.findBySQL(sql, paramMap).get(0);
			userFriendDao.delete(userFriend);
		}else{
			return Constants.ERROR;
		}
		return 1;
	}

	//拉取用户粉丝列表
	public List<UserBean> getMyFans(Integer userId){
		List<UserBean> friends = new ArrayList<UserBean>();
		List<UserFriend> list = new ArrayList<UserFriend>();
		list = userFriendDao.findByProperty("friendId", userId);
		for (UserFriend userFriend : list) {
			UserBean friend = new UserBean();
			friend = fetchUserBean(userFriend.getUserId());
			friends.add(friend);
		}
		return friends;
	}

	//根据我关注的人所关注的人来推荐；推荐的好友不能是我的好友
	public List<RecommendFriendBean> getRecommendFriends(Integer userId){
		List<RecommendFriendBean> friends = new ArrayList<RecommendFriendBean>();
		//被推荐的好友以及来自哪个好友
		Map<Integer, List<Integer>> friend_relations = new HashMap<Integer,List<Integer>>();
		List<UserFriend> myFriends = new ArrayList<UserFriend>();
		List<Integer> myFriendIds = new ArrayList<Integer>();
		myFriends = userFriendDao.findByProperty("userId", userId);
		//只推荐那些不在我的好友列表中的好友
		for(UserFriend friend : myFriends)
			myFriendIds.add(friend.getFriendId());
		for(Integer friendId : myFriendIds){
			List<UserFriend> friendsOfFriend = new ArrayList<UserFriend>();
			friendsOfFriend = userFriendDao.findByProperty("userId", friendId);
			for(UserFriend friend: friendsOfFriend){
				//只加入那些不在我的关注列表中的好友的好友
				if(!myFriendIds.contains(friend.getFriendId()) && friend.getFriendId() != userId){
					if(friend_relations.containsKey(friend.getFriendId()))
						friend_relations.get(friend.getFriendId()).add(friend.getUserId());
					else{
						friend_relations.put(friend.getFriendId(), new ArrayList<Integer>());
						friend_relations.get(friend.getFriendId()).add(friend.getUserId());
					}
				}
			}
		}
		for(Map.Entry<Integer, List<Integer>> entry : friend_relations.entrySet()){
			UserBean user = new UserBean();
			RecommendFriendBean bean = new RecommendFriendBean();
			String fromNames = new String();
			user = fetchUserBean(entry.getKey());
			bean = new RecommendFriendBean(user);
			for(Integer fromId: entry.getValue()){
				fromNames += ",";
				fromNames += userInfoDao.findUniqueByProperty("userId", fromId).getNickname();
			}
			fromNames = fromNames.substring(fromNames.indexOf(",")+1);
			bean.setIntermediary(fromNames);
			friends.add(bean);
		}
		//返回结果中的至多5个
		if(friends.size() <= 5)
			return friends;
		else
		{
			return friends.subList(0,6);
		}
	}

	//获得标签库
	public List<String> getTagLib() {
		List<UserTagLib> tags = taglibDao.findAll();
		List<String> tagnames = new ArrayList<String>();

		for(UserTagLib ele:tags){
			tagnames.add(ele.getTagName());
		}
		return tagnames;
	}

	//获得自定义标签
	public List<String> getMyTags(Integer userId) {
		List<UserSetTags> tags = settagDao.findByProperty("userId",userId);
		List<String> tagcontents = new ArrayList<String>();

		for(UserSetTags ele:tags) {
			if (ele.getTagId() != null) {
				List<UserTagLib> libtag = taglibDao.findByProperty("tagId", ele.getTagId());
				if (!libtag.isEmpty()) {
					tagcontents.add(libtag.get(0).getTagName());
				}
			}
		}
		return tagcontents;
	}

	//添加tag，前台传来的是标签列表
	public void addTag(Integer userId/*, Integer tagId*/, String tagContent) throws JSONException {
			
		String[] tags = tagContent.trim().split("#");
		List<Integer> insertTagIdList = new ArrayList<Integer>();
		for(int i = 0;i < tags.length; i++){
			tags[i] = tags[i].trim();
			if (tags[i].length() == 0) {
				continue;
			}
			List<UserTagLib> libtag = taglibDao.findByProperty("tagName", tags[i]);
			//如果不是从标签库里获得
			if(libtag.size() < 1) {
				taglibDao.save(new UserTagLib(null, tags[i]));
			}
			int tagId = taglibDao.lookUpTag(tags[i]);
			if (tagId != -1) {
				int utagId = settagDao.lookUpTag(userId, tagId);
				if (utagId == -1) {
					UserSetTags utag = new UserSetTags();
					utag.setUserId(userId);
					utag.setTagId(tagId);
					insertTagIdList.add(tagId);
					settagDao.save(utag);
				}
			}
		}
		
		JSONObject root = new JSONObject();
        root.put("method", RecommenderUtils.insertUserTagMethod);
        JSONObject params = new JSONObject();
        params.put("id", userId);
        params.put("tags", insertTagIdList);
        root.put("params", params);
		BaseHttpClient httpClient = new BaseHttpClient(RecommenderUtils.recommenderUrl);
		JSONObject response = httpClient.post(root);
	}

	@Override
	public List<SchoolsRegionsBean> getAllSchoolsAndRegions() { 
		return schoolDao.getAllSchoolsAndRegions();
	}

	//修改个人信息
	public void modifyInfo(int userId, String picture, String nickName,
			int schoolId, int regionId, String tagContent) throws JSONException {
		UserInfo uinfo = this.userInfoDao.get(userId);
		if (picture != null && picture.length() > 0) {
			String fileName =String.valueOf(new Date().getTime());
			File dir = new File(ServletActionContext.getServletContext().getRealPath("HeadImages"));
			File file = new File(dir, fileName);
			//   System.out.println(fileName+"  "+filePath);
			try {
				FileOutputStream out = new FileOutputStream(file);
				//System.out.println(out.toString());
				ByteArrayInputStream in = new ByteArrayInputStream(picture.getBytes("ISO-8859-1"));
				//System.out.println(picture);
				byte[] buffer = new byte[1024 * 1024];
				int to_write = in.read(buffer);
				while (to_write > 0) {
					out.write(buffer, 0, to_write);
					to_write = in.read(buffer);
				}
				out.flush();
				out.close();
				/*	    	File src = new File(picture);
		    	File dst = new File(filePath);
		    	imageService.copy(src, dst);*/
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			uinfo.setPicture(fileName);
		}
		if (nickName != null && nickName.length() > 0) {
			uinfo.setNickname(nickName);
		}
		uinfo.setSchoolId(schoolId);
		uinfo.setRegionId(regionId);
		userInfoDao.saveOrUpdate(uinfo);

		addTag(userId,tagContent);
	}

	@Override
	public List<ModuleObjectBean> recommendInfos() {
		int recNum =  (int)(Math.random()*10)+1 ;//获得随机数

		List<ModuleObjectBean> results = new ArrayList<ModuleObjectBean>();

		/*ModuleObjectBean tempbean = new ModuleObjectBean();
		tempbean.setModuleId(ele.getModuleId());
		tempbean.setModuleName(Constants.Tabmap.get(ele.getModuleId()));*/

		//下面根据不同的Module从不同的表中获得相应的信息

		//校园周边的记录
		if(schoolAroundItemDao.findUniqueByProperty("itemId",recNum)!=null){
			SchoolAroundItem saitem = schoolAroundItemDao.findUniqueByProperty("itemId",recNum); //如果是校园周边，则itemId一定不为空
			ModuleObjectBean tempbean = new ModuleObjectBean();
			tempbean.setModuleId(1);
			tempbean.setModuleName(Constants.Tabmap.get(1));
			tempbean.setItemId(saitem.getItemId());
			tempbean.setTitle(saitem.getItemName());
			tempbean.setContent(saitem.getItemDescription());
			tempbean.setPicture(saitem.getItemPic());
			tempbean.setLatitude(saitem.getLatitude());
			tempbean.setLongitude(saitem.getLongitude());
			tempbean.setItemLocation(saitem.getItemLocation());
			tempbean.setItemTel(saitem.getItemTel());

			//看是否有优惠信息
			Advertise ad = adverDao.findUniqueByProperty("adId",recNum);
			if(ad != null){
				tempbean.setAdverId(ad.getAdId());
				tempbean.setAdverFlag(true);
			}	
			results.add(tempbean);
		}

		//校园资讯
		if(schoolnewsDao.findUniqueByProperty("topicId",recNum)!=null){
			SchoolNews news = schoolnewsDao.findUniqueByProperty("topicId",recNum);  
			ModuleObjectBean tempbean = new ModuleObjectBean();
			tempbean.setModuleId(2);
			tempbean.setModuleName(Constants.Tabmap.get(2));
			tempbean.setItemId(recNum);
			tempbean.setTitle(news.getTitle());
			//tempbean.setContent(news.getContent());
			String time = String.valueOf(news.getDate());
			tempbean.setDate(time.substring(0, time.length()-2));
			results.add(tempbean);
		}

		//兼职招聘
		if (partTimeJobDao.findUniqueByProperty("jobId",recNum)!=null){
			PartTimeJob ptj = partTimeJobDao.findUniqueByProperty("jobId",recNum);
			ModuleObjectBean tempbean = new ModuleObjectBean();
			tempbean.setModuleId(3);
			tempbean.setModuleName(Constants.Tabmap.get(3));
			tempbean.setItemId(ptj.getJobId());
			tempbean.setTitle(ptj.getJobname());
			tempbean.setContent(ptj.getContent());
			String time = String.valueOf(ptj.getDate());
			tempbean.setDate(time.substring(0, time.length()-2));  
			results.add(tempbean);
		}

		//二手或者鹊桥
		if (shlvDao.findUniqueByProperty("shId",recNum)!=null){
			ShLVInfo slv = shlvDao.findUniqueByProperty("shId",recNum);
			ModuleObjectBean tempbean = new ModuleObjectBean();
			if(slv.getType() == 1){
				tempbean.setModuleId(4);
				tempbean.setModuleName(Constants.Tabmap.get(4));
			}else{
				tempbean.setModuleId(5);
				tempbean.setModuleName(Constants.Tabmap.get(5));
			}
			tempbean.setItemId(slv.getShId());
			tempbean.setTitle(slv.getTitle());
			tempbean.setContent(slv.getDescription());
			tempbean.setPicture(slv.getPicture());
			String time = String.valueOf(slv.getTimestamp());
			tempbean.setDate(time.substring(0, time.length()-2));  
			results.add(tempbean);
		}

		//互动墙不能收藏

		//校园活动
		/*if (activityDao.findUniqueByProperty("activityId",recNum)!=null){
			SchoolActivity activity = activityDao.findUniqueByProperty("activityId",recNum);
			ModuleObjectBean tempbean = new ModuleObjectBean();
			tempbean.setModuleId(7);
			tempbean.setModuleName(Constants.Tabmap.get(7));
			tempbean.setItemId(activity.getActivityId());
			tempbean.setTitle(activity.getTitle());
			tempbean.setContent(activity.getContent());
			tempbean.setPicture(activity.getPicture());
			String time = String.valueOf(activity.getDate());
			tempbean.setDate(time.substring(0, time.length()-2));  
			results.add(tempbean);
		}*/
		return results;
	}

	@Override
	public void updateScore(int userId, int score) {
		System.out.println("-----------------"+userId+score);
		UserInfo userInfo = userInfoDao.findUniqueByProperty("userId", userId);
		userInfo.setPoint(score);
		userInfoDao.saveOrUpdate(userInfo);
	}


}
