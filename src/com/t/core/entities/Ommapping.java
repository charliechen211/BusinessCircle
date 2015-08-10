package com.t.core.entities;

/**
 * OmmappingId entity. @author MyEclipse Persistence Tools
 */

public class Ommapping implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6072652772240916077L;
	
	
	public Integer oMMappingId;
	public Integer ownerId;
	public Integer merchantId;
	
	
	public Ommapping(){}

	public Integer getoMMappingId() {
		return oMMappingId;
	}



	public void setoMMappingId(Integer oMMappingId) {
		this.oMMappingId = oMMappingId;
	}



	public Integer getOwnerId() {
		return ownerId;
	}


	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}


	public Integer getMerchantId() {
		return merchantId;
	}


	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}

	

}