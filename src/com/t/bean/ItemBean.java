package com.t.bean;

import java.util.List;

import com.t.core.entities.Item;

/*
 * By liangyunlong
 */

public class ItemBean {

	private String itemName;
	private Integer itemId;
	private String picture;
	private Float rate;
	private Float price;
	private List<String> tags;
	public ItemBean(){}
	public ItemBean(Item item){
		this.itemName = item.getItemName();
		this.itemId = item.getItemId();
		this.picture = item.getPicture();
		this.rate = Float.valueOf(String.valueOf(item.getRate()));
		this.price = Float.valueOf(String.valueOf(item.getPrice()));
	}
	public ItemBean(
			String itemName,
			Integer itemId,
			String picture,
			Float rate,
			Float price){
		this.itemName = itemName;
		this.itemId = itemId;
		this.rate = rate;
		this.picture = picture;
		this.price = price;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Float getRate() {
		return rate;
	}

	public void setRate(Float rate) {
		this.rate = rate;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}
	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	
}
