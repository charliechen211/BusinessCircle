package com.t.core.entities;

public class TagEntity {

	private Integer tagEntityId;
	private Integer tagId;
	private Integer entityId;
	private Integer type;
	private Double weight;
	private String tagName;
	public TagEntity(){}
	public TagEntity(Integer tagEntityId, Integer tagId, Integer entityId,
			Integer type, Double weight, String tagName) {
		super();
		this.tagEntityId = tagEntityId;
		this.tagId = tagId;
		this.entityId = entityId;
		this.type = type;
		this.weight = weight;
		this.tagName = tagName;
	}
	public Integer getTagEntityId() {
		return tagEntityId;
	}
	public void setTagEntityId(Integer tagEntityId) {
		this.tagEntityId = tagEntityId;
	}
	public Integer getTagId() {
		return tagId;
	}
	public void setTagId(Integer tagId) {
		this.tagId = tagId;
	}
	public Integer getEntityId() {
		return entityId;
	}
	public void setEntityId(Integer entityId) {
		this.entityId = entityId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	
	@Override
    public int hashCode() {
        // TODO Auto-generated method stub
        return tagId;
    }
     
    @Override
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        return this.tagId.equals(((TagEntity)obj).tagId);
    }	
	
}
