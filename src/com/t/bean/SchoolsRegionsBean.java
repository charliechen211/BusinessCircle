package com.t.bean;

import java.util.List;
import com.t.core.entities.School;
import com.t.core.entities.SchoolRegion;
/**
 * 一次性获得所有学校和校区的信息
 * @author WangZhaoLi
 *
 */

public class SchoolsRegionsBean {
	 
	public School school;
	public List<SchoolRegion> regions;
	
	public SchoolsRegionsBean(){
		
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public List<SchoolRegion> getRegions() {
		return regions;
	}

	public void setRegions(List<SchoolRegion> regions) {
		this.regions = regions;
	}
	 
	
}
