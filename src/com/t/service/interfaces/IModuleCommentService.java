package com.t.service.interfaces;

import java.util.List;

import com.t.bean.ModuleCommentBean;

 

public interface IModuleCommentService {
	
	public List<ModuleCommentBean> fetchComments(Integer objectId, Integer type);
	public Integer addComment(Integer userId, Integer objectId, String comment, Integer type);
	public Integer setGood(Integer userId, Integer objectId, Integer type);

}
