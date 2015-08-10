package com.t.core.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.t.core.entities.ShLVInfo;
import com.t.utils.BaseDao;


@Repository
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ShInfoDao extends BaseDao<ShLVInfo, Integer> {
	
	public ShInfoDao(){
		this.entityClass = ShLVInfo.class;
	}
	
	@SuppressWarnings("unchecked")
	public List<ShLVInfo> getInfos(Integer circleId, Integer type, Integer pageId, Integer pageSize)
	{
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("circleId", circleId);
		paramMap.put("type", type);
		paramMap.put("delFlg", 0);
		paramMap.put("pageId", pageId);
		paramMap.put("pageSize", pageSize);
//		+pageId+", "+pageSize
		List<ShLVInfo> infoList = this.findBySQL(
				"SELECT * from shlvInfo where circleId = :circleId and type = :type and delFlg = :delFlg order by timestamp desc limit :pageId, :pageSize", paramMap);
		return infoList;
	}

}
