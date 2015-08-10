package com.t.service.interfaces;

import java.util.List;

import com.t.core.entities.TagEntity;

public interface ITagEntityService {
	public  List<TagEntity> getUserTags(int userId);
	public  List<TagEntity> getMerchanTags(int merchantid);
}
