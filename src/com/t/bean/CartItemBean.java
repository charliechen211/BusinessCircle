package com.t.bean;

import com.t.core.entities.Item;
//本类为购物车中商品详情显示
/*
 * By liangyunlong
 */
public class CartItemBean extends ItemBean{
	private Integer number;
	public CartItemBean(){
		super();
	}
	public CartItemBean(Item item){
		super(item);
	}
	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}
	

}
