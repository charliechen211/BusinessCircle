package com.t.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.t.bean.SchoolsRegionsBean;
import com.t.core.entities.School;
import com.t.core.entities.SchoolRegion;
import com.t.utils.BaseDao;

@Repository
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class SchoolDao extends BaseDao<School,Integer> {
	//private static final Logger log = LoggerFactory.getLogger(ItemDao.class);
	@Autowired
	public SchoolRegionDao regionDao;

	public SchoolDao () {
		this.entityClass = School.class;
	}

	//通过学校Id获得名字
	public School getSchool(int schoolId){	 
		return  this.findByProperty("schoolId", schoolId).get(0);	 
	}

	//通过所有学校
	public List<SchoolsRegionsBean> getAllSchoolsAndRegions(){	
		List<School> schools = this.findAll();	
		List<SchoolsRegionsBean> srbeans = new ArrayList<SchoolsRegionsBean>();

		for(School ele:schools){
			SchoolsRegionsBean bean = new SchoolsRegionsBean();
			List<SchoolRegion> regions = regionDao.getSchoolRegions(ele.getSchoolId());
			bean.setSchool(ele);
			bean.setRegions(regions);
			srbeans.add(bean);
		}
		return srbeans;
	}

}
