package com.t.core.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Multiset.Entry;
import com.t.core.entities.TagEntity;
import com.t.utils.BaseDao;
import com.t.utils.Constants;

@Repository
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class TagEntityDao extends BaseDao<TagEntity, Integer>{

	public TagEntityDao()
	{
		this.entityClass = TagEntity.class;
	}
	
	public List<TagEntity> getMerchantTags(int merchantId)
	{
//		List<TagEntity> tags = this.findByCriteria(Restrictions.eq("entityId", merchantId), Restrictions.eq("type", Constants.TAG_TYPE_MERCHANT));
//		Map<TagEntity, Integer> tagsMap = new LinkedHashMap<TagEntity, Integer>();
//		for(TagEntity tag: tags) {
//			if(tagsMap.containsKey(tag)) {
//				tagsMap.put(tag, tagsMap.get(tag)+1);
//			}
//			else {
//				tagsMap.put(tag, 1);				
//			}
//		}
//		Map<TagEntity, Integer> sortedTagsMap = sortMapByValue(tagsMap);
//		return mapToList(sortedTagsMap);
		
		return this.findByCriteria(Restrictions.eq("entityId", merchantId), Restrictions.eq("type", Constants.TAG_TYPE_MERCHANT));
	}
	
	public List<TagEntity> getUserTags(int userId)
	{
		List<TagEntity> tags = this.findByCriteria(Restrictions.eq("entityId", userId), Restrictions.eq("type", Constants.TAG_TYPE_USER));
		Map<TagEntity, Integer> tagsMap = new LinkedHashMap<TagEntity, Integer>();
		for(TagEntity tag: tags) {
			if(tagsMap.containsKey(tag)) {
				tagsMap.put(tag, tagsMap.get(tag)+1);
			}
			else {
				tagsMap.put(tag, 1);				
			}
		}
		Map<TagEntity, Integer> sortedTagsMap = sortMapByValue(tagsMap);
		return mapToList(sortedTagsMap);
		
//		return this.findByCriteria(Restrictions.eq("entityId", userId), Restrictions.eq("type", Constants.TAG_TYPE_USER));
	}
	public List<TagEntity> getItemTags(int itemId)
	{
		List<TagEntity> tags = this.findByCriteria(Restrictions.eq("entityId", itemId), Restrictions.eq("type", Constants.TAG_TYPE_ITEM));
		Map<TagEntity, Integer> tagsMap = new LinkedHashMap<TagEntity, Integer>();
		for(TagEntity tag: tags) {
			if(tagsMap.containsKey(tag)) {
				tagsMap.put(tag, tagsMap.get(tag)+1);
			}
			else {
				tagsMap.put(tag, 1);				
			}
		}
		Map<TagEntity, Integer> sortedTagsMap = sortMapByValue(tagsMap);
		return mapToList(sortedTagsMap);
		
//		return this.findByCriteria(Restrictions.eq("entityId", itemId), Restrictions.eq("type", Constants.TAG_TYPE_ITEM));
	}
	
	private Map<TagEntity, Integer> sortMapByValue(Map<TagEntity, Integer> oriMap) {
		Map<TagEntity, Integer> sortedMap = new LinkedHashMap<TagEntity, Integer>();
		if (oriMap != null && !oriMap.isEmpty()) {
			List<Map.Entry<TagEntity, Integer>> entryList = new ArrayList<Map.Entry<TagEntity, Integer>>(oriMap.entrySet());
			Collections.sort(entryList,
					new Comparator<java.util.Map.Entry<TagEntity, Integer>>() {
						@Override
						public int compare(java.util.Map.Entry<TagEntity, Integer> entry1,
								java.util.Map.Entry<TagEntity, Integer> entry2) {
							int value1 = 0, value2 = 0;
							try {
								value1 = entry1.getValue();
								value2 = entry2.getValue();
							} catch (NumberFormatException e) {
								value1 = 0;
								value2 = 0;
							}
							return value2 - value1;
						}
					});
			Iterator<Map.Entry<TagEntity, Integer>> iter = entryList.iterator();
			Map.Entry<TagEntity, Integer> tmpEntry = null;
			while (iter.hasNext()) {
				tmpEntry = iter.next();
				sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());
			}
		}
		return sortedMap;
	}
	
	private List<TagEntity> mapToList(Map<TagEntity, Integer> oriMap) {
		List<TagEntity> tagList = new ArrayList<TagEntity>();
		if (oriMap != null && !oriMap.isEmpty()) {
			List<Map.Entry<TagEntity, Integer>> entryList = new ArrayList<Map.Entry<TagEntity, Integer>>(oriMap.entrySet());
			Iterator<Map.Entry<TagEntity, Integer>> iter = entryList.iterator();
			Map.Entry<TagEntity, Integer> tmpEntry = null;
			while (iter.hasNext()) {
				tmpEntry = iter.next();
				tagList.add(tmpEntry.getKey());
			}
		}
		return tagList;
	}
	
}
