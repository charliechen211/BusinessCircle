package com.t.core.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.t.core.entities.Comment;
import com.t.core.entities.SchoolAroundItem;
import com.t.utils.BaseDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class SchoolAroundItemDao extends BaseDao<SchoolAroundItem,Integer>{
	public SchoolAroundItemDao (){
		this.entityClass = SchoolAroundItem.class;
	}

	@SuppressWarnings("unchecked")
	public List<SchoolAroundItem> getItemList(Integer typeId,Integer objectId){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("typeId", typeId);
		paramMap.put("objectId", objectId);
		List<SchoolAroundItem> list = this.findBySQL(
				"SELECT * from school_around_item where typeId = :typeId and " +
				"objectId = :objectId", paramMap);
		return list;
	}
}
