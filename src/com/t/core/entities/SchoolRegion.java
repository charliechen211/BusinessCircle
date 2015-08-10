package com.t.core.entities;

/**
 * 学校的各个校区实体
 */

public class SchoolRegion implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private int Id;
	private int schoolId;
	private int regionId;
	private String regionName;


	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public int getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(int schoolId) {
		this.schoolId = schoolId;
	}
	public int getRegionId() {
		return regionId;
	}
	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}




}