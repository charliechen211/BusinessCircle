package com.t.service.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.t.core.dao.TagEntityDao;
import com.t.core.entities.TagEntity;
import com.t.service.interfaces.ITagEntityService;
import com.t.utils.Constants;


@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class TagEntityService implements ITagEntityService{

	@Autowired
	private TagEntityDao tagEntityDao;
	@Override
	public List<TagEntity> getUserTags(int userId) {
		Criteria criteria = tagEntityDao.createCriteria(Restrictions.eq("entityId", userId),Restrictions.eq("type", Constants.TAG_TYPE_USER));
		List<TagEntity> tagEntitys = tagEntityDao.findByCriteria(criteria);
		return tagEntitys;
	}

	@Override
	public List<TagEntity> getMerchanTags(int merchantid) {
		Criteria criteria = tagEntityDao.createCriteria(Restrictions.eq("entityId", merchantid),Restrictions.eq("type", Constants.TAG_TYPE_MERCHANT));
		List<TagEntity> tagEntitys = tagEntityDao.findByCriteria(criteria);
		return tagEntitys;
	}
}
