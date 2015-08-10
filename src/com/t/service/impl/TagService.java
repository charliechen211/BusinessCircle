package com.t.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.t.core.dao.TagDao;
import com.t.core.dao.TagEntityDao;
import com.t.core.dao.TagRecordDao;
import com.t.core.entities.Tag;
import com.t.core.entities.TagEntity;
import com.t.core.entities.TagRecord;
import com.t.service.interfaces.ITagService;


@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class TagService implements ITagService{

	@Autowired
	private TagDao tagDao;
	
	@Autowired
	private TagRecordDao tagRecordDao;
	
	@Autowired
	private TagEntityDao tagEntityDao;
	
	@Override
	public Boolean insertTag(
			Integer userId,
			Integer entityId,
			Long entityType,
			List<String> content) {
		// TODO Auto-generated method stub
		
		List<TagRecord> tagList = new ArrayList<TagRecord>();
		if(content.size() == 0)
			return false;
		for(int i=0; i<content.size(); i++){
			System.out.println("content: "+content.get(i));
			TagRecord tagRecord = 
				new TagRecord(
					null,
					userId,
					entityId,
					null,
					entityType,
					content.get(i));
			tagList.add(tagRecord);
		}
		
		for(int i=0; i<tagList.size(); i++){
			TagRecord thisTag = tagList.get(i);
			int tagId = tagDao.lookUpTag(thisTag.getTag_1());
			if(tagId==-1)
				tagDao.save(new Tag(null, thisTag.getTag_1()));
			thisTag.setTag(tagDao.lookUpTag(thisTag.getTag_1()));
			
			tagRecordDao.save(thisTag);
			
			TagEntity tagEntity = 
				new TagEntity(
					null,
					thisTag.getTag(),
					thisTag.getEntityId(),
					Integer.valueOf(thisTag.getEntityType().toString()),
					null,
					thisTag.getTag_1());
			
			tagEntityDao.save(tagEntity);
		}
		return true;
	}
}


