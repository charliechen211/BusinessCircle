package com.t.core.entities;

/**
 * 前台用户进行设置时打标签的标签库所在表
 * @author WangZhaoLi
 *
 */
public class UserTagLib {
	
	private Integer tagId;
	private String tagName;
	
	public UserTagLib() {}
	
	public UserTagLib(Integer tagId, String tagName) {
		this.tagId = tagId;
		this.tagName = tagName;
	}

	public Integer getTagId() {
		return tagId;
	}

	public void setTagId(Integer tagId) {
		this.tagId = tagId;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	
	
}
