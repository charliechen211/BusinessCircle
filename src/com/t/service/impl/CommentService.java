package com.t.service.impl;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.t.bean.CommentBean;
import com.t.core.dao.CommentDao;
import com.t.core.dao.UserInfoDao;
import com.t.core.entities.Comment;
import com.t.core.entities.UserInfo;
import com.t.service.interfaces.ICommentService;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class CommentService  implements ICommentService{

	@Autowired
	private CommentDao commentDao;
	@Autowired
	private UserInfoDao userInfoDao;
	//拉取商品或商铺的评论信息
	public List<CommentBean> fetchComments(Integer entityId,Integer entityType){
		List<CommentBean> commentBeans = new ArrayList<CommentBean>();
		List<Comment> comments = new ArrayList<Comment>();
		comments = commentDao.getCommentsByIdAndType(entityId, entityType);
		for (Comment comment : comments) {
			CommentBean commentBean = new CommentBean(comment);
			UserInfo userInfo = new UserInfo();
			if(userInfoDao.findByProperty("userId", comment.getUserId()).size() > 0)
				userInfo = userInfoDao.findByProperty("userId", comment.getUserId()).get(0);
			commentBean.setUserName(userInfo.getNickname());
			commentBeans.add(commentBean);
		}
		return commentBeans;
	}
	//添加评论信息
	public Integer addComments(Integer entityId,Integer entityType,Integer userId,String content,Double rate1,Double rate2,Double rate3,Double consume){
		Comment comment = new Comment(null,entityId,entityType,userId,content,rate1,rate2,rate3,null,consume);
		return commentDao.save(comment);
	}
	
	@SuppressWarnings("unchecked")
	public List<Float> getAnalysisResult(int merchantId) {//获得平均值
		float a=0,b=0,c=0;
		List<Float> result = new ArrayList<Float>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("merchantId", merchantId);
		List<Comment> temp = commentDao.findBySQL("select * from Comment where entityId =:merchantId  and entityType=1 ", paramMap);
	   
		if(temp.size()>0){
		DecimalFormat df = new DecimalFormat("#.00");//保留1位小数，否则json不让传输 
		for(Comment element:temp){
		   a += element.getRate1();
		   b += element.getRate2();
		   c += element.getRate3();
	   }
	    a = a/temp.size();
	    b = b/temp.size();
	    c = c/temp.size();
	    result.add(Float.parseFloat(df.format(a)));
	    result.add(Float.parseFloat(df.format(b)));
	    result.add(Float.parseFloat(df.format(c)));
		}else{//如果没有值，就默认为0
			result.add(0.0f);
			result.add(0.0f);
			result.add(0.0f);	
		}
	    return result;
	}
	
}
