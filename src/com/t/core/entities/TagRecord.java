package com.t.core.entities;

import java.sql.Timestamp;


/**
 * TagRecord entity. @author MyEclipse Persistence Tools
 */

public class TagRecord  implements java.io.Serializable {


    // Fields    

     private Integer recordId;
     private Integer tag;
     private Integer userId;
     private Integer entityId;
     private Timestamp timestamp;
     private Long entityType;
     private String tag_1;


    // Constructors

    /** default constructor */
    public TagRecord() {
    }

	/** minimal constructor */
    public TagRecord(Integer tag, Integer userId, Integer entityId, String tag_1) {
        this.tag = tag;
        this.userId = userId;
        this.entityId = entityId;
        this.tag_1 = tag_1;
    }
    
    /** full constructor */
    public TagRecord(Integer tag, Integer userId, Integer entityId, Timestamp timestamp, Long entityType, String tag_1) {
        this.tag = tag;
        this.userId = userId;
        this.entityId = entityId;
        this.timestamp = timestamp;
        this.entityType = entityType;
        this.tag_1 = tag_1;
    }

   
    // Property accessors

    public Integer getRecordId() {
        return this.recordId;
    }
    
    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public Integer getTag() {
        return this.tag;
    }
    
    public void setTag(Integer tag) {
        this.tag = tag;
    }

    public Integer getUserId() {
        return this.userId;
    }
    
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getEntityId() {
        return this.entityId;
    }
    
    public void setEntityId(Integer entityId) {
        this.entityId = entityId;
    }

    public Timestamp getTimestamp() {
        return this.timestamp;
    }
    
    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public Long getEntityType() {
        return this.entityType;
    }
    
    public void setEntityType(Long entityType) {
        this.entityType = entityType;
    }

    public String getTag_1() {
        return this.tag_1;
    }
    
    public void setTag_1(String tag_1) {
        this.tag_1 = tag_1;
    }
   








}