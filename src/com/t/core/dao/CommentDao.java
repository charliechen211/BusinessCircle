package com.t.core.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.t.core.entities.Comment;
import com.t.utils.BaseDao;


@Repository
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class CommentDao extends BaseDao<Comment, Integer> {
	
	public CommentDao(){
		this.entityClass = Comment.class;
	}
	@SuppressWarnings("unchecked")
	public List<Comment> getCommentsByIdAndType(Integer entityId,Integer entityType)
	{
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("entityId", entityId);
		paramMap.put("entityType", entityType);
		List<Comment> commentList = this.findBySQL(
				"SELECT * from Comment where entityId = :entityId and " +
				"entityType = :entityType  order by timestamp desc", paramMap);
		return commentList;
	}

}
