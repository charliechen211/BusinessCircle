package com.t.core.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.t.core.entities.ModuleComment;
import com.t.utils.BaseDao;

@Repository
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ModuleCommentDao extends BaseDao<ModuleComment, Integer> {
	
	public ModuleCommentDao(){
		this.entityClass = ModuleComment.class;
	}
	
	@SuppressWarnings("unchecked")
	public List<ModuleComment> getCommentsByobjectId(Integer objectId, Integer type)
	{
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("objectId", objectId);
		paramMap.put("type", type);
		paramMap.put("delFlg", 0);
		List<ModuleComment> commentList = this.findBySQL(
				"SELECT * from ModuleComment where objectId = :objectId and type = :type and delFlg = :delFlg order by timestamp desc", paramMap);
		return commentList;
	}

}
