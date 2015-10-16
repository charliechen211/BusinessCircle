package com.t.service.interfaces;

import java.util.List;

import org.json.JSONException;

import com.t.core.entities.TagEntity;

public interface ITagService {
	public Boolean insertTag(
			Integer userId,
			Integer entityId,
			Long entityType,
			List<String> tags) throws JSONException;
}
