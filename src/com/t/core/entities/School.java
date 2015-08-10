package com.t.core.entities;

/**
 * 学校实体，考虑未来可能会接入其他学校
 */

public class School implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private int Id;
	private int schoolId;
	private String schoolName;
		
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		this.Id = id;
	}
	public int getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(int schoolId) {
		this.schoolId = schoolId;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	

	
}