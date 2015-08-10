package com.t.core.entities;

import java.util.HashSet;
import java.util.Set;

/**
 * Type entity. @author MyEclipse Persistence Tools
 */

public class Type implements java.io.Serializable {

	// Fields

	private Integer typeId;
	private Integer typeParent;
	private String typeName;
	private Set items = new HashSet(0);
	private Set merchants = new HashSet(0);

	// Constructors

	/** default constructor */
	public Type() {
	}

	/** minimal constructor */
	public Type(Integer typeId, Integer typeParent) {
		this.typeId = typeId;
		this.typeParent = typeParent;
	}

	/** full constructor */
	public Type(Integer typeId, Integer typeParent, String typeName, Set items,
			Set merchants) {
		this.typeId = typeId;
		this.typeParent = typeParent;
		this.typeName = typeName;
		this.items = items;
		this.merchants = merchants;
	}

	// Property accessors

	public Integer getTypeId() {
		return this.typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public Integer getTypeParent() {
		return this.typeParent;
	}

	public void setTypeParent(Integer typeParent) {
		this.typeParent = typeParent;
	}

	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Set getItems() {
		return this.items;
	}

	public void setItems(Set items) {
		this.items = items;
	}

	public Set getMerchants() {
		return this.merchants;
	}

	public void setMerchants(Set merchants) {
		this.merchants = merchants;
	}

}