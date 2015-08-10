package com.t.core.entities;



/**
 * MerchantOwner entity. @author MyEclipse Persistence Tools
 */

public class MerchantOwner  implements java.io.Serializable {

	 private static final long serialVersionUID = 7807519748687543536L;
	 private Integer ownerId;
     private String ownerName;
     private String passwd;
     private String telNumber;
     private String idNumber;
     
     private String checkStatus;

    /** default constructor */
    public MerchantOwner() {
    }

    
    /** full constructor */
    public MerchantOwner(String ownerName, String passwd, String telNumber, String idNumber) {
        this.ownerName = ownerName;
        this.passwd = passwd;
        this.telNumber = telNumber;
        this.idNumber = idNumber;
    }

   
    // Property accessors

    public Integer getOwnerId() {
        return this.ownerId;
    }
    
    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerName() {
        return this.ownerName;
    }
    
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getPasswd() {
        return this.passwd;
    }
    
    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getTelNumber() {
        return this.telNumber;
    }
    
    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public String getIdNumber() {
        return this.idNumber;
    }
    
    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

	public String getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}
   

}