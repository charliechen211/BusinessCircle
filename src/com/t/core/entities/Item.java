package com.t.core.entities;
// default package

import java.util.HashSet;
import java.util.Set;


/**
 * Item entity. @author MyEclipse Persistence Tools
 */

public class Item  implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	 private Integer itemId;
     private Integer type;
     private Integer merchant;
     private String itemName;
     private String description;
     private Double price;
     private String picture;
     private Double rate;
     private Set comments = new HashSet(0);
     private Set orders = new HashSet(0);

     private String checkStatus;
    // Constructors

    /** default constructor */
    public Item() {
    }

	/** minimal constructor */
    public Item(int merchant, String itemName) {
        this.merchant = merchant;
        this.itemName = itemName;
    }
    
    /** full constructor */
    public Item(Integer type, Integer merchant, String itemName, String description, Double price, String picture, Double rate, Set comments, Set orders) {
        this.type = type;
        this.merchant = merchant;
        this.itemName = itemName;
        this.description = description;
        this.price = price;
        this.picture = picture;
        this.rate = rate;
        this.comments = comments;
        this.orders = orders;
    }

   
    // Property accessors

    public Integer getItemId() {
        return this.itemId;
    }
    
    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getType() {
        return this.type;
    }
    
    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getMerchant() {
        return this.merchant;
    }
    
    public void setMerchant(Integer merchant) {
        this.merchant = merchant;
    }

    public String getItemName() {
        return this.itemName;
    }
    
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return this.price;
    }
    
    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPicture() {
        return this.picture;
    }
    
    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Double getRate() {
        return this.rate;
    }
    
    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Set getComments() {
        return this.comments;
    }
    
    public void setComments(Set comments) {
        this.comments = comments;
    }

    public Set getOrders() {
        return this.orders;
    }
    
    public void setOrders(Set orders) {
        this.orders = orders;
    }

	public String getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}
   
    
}