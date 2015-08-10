package com.t.core.entities;

/**
 * 前台用户自己选择以及自定义的标签记录表
 * @author WangZhaoLi
 *
 */
public class UserSetTags {
	
	private Integer Id;
	private Integer userId;
	private Integer tagId;
	private String tagContent;
	
	public UserSetTags() {}

	public Integer getTagId() {
		return tagId;
	}

	public void setTagId(Integer tagId) {
		this.tagId = tagId;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getTagContent() {
		return tagContent;
	}

	public void setTagContent(String tagContent) {
		this.tagContent = tagContent;
	}
	
}
