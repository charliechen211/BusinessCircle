package com.t.service.interfaces;

import java.util.List;

import com.t.bean.CommentBean;

public interface ICommentService {
	public List<CommentBean> fetchComments(Integer entityId,Integer entityType);
	public Integer addComments(Integer entityId,Integer entityType,Integer userId,String content,Double rate1,Double rate2,Double rate3,Double consume);
  
	public List<Float> getAnalysisResult(int merchantId);

}
