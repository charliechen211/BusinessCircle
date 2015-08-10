package com.t.bean;

import com.t.core.entities.Merchant;

public class SearchResultBean {
	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}


	public void setShopBean(Merchant shopBean) {
		this.shopBean = shopBean;
	}

	public Merchant getShopBean() {
		return shopBean;
	}


	private Merchant shopBean;
	private float weight;
}
