package com.t.core.dao;


import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.t.core.entities.SchoolRegion;
import com.t.utils.BaseDao;

@Repository
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class SchoolRegionDao extends BaseDao<SchoolRegion,Integer> {
	//private static final Logger log = LoggerFactory.getLogger(ItemDao.class);

	public SchoolRegionDao () {
		this.entityClass = SchoolRegion.class;
	}

	//通过学校Id和校区Id获得校区名
	public SchoolRegion getSchoolRegion(int schoolId,int regionId){	 	
		Query q = this.createQuery("from SchoolRegion a where a.schoolId =:sid and a.regionId =:rid");
		q.setInteger("sid",schoolId);
		q.setInteger("rid", regionId);
		return (SchoolRegion) q.list().get(0); 
	}

	//通过学校Id获取所有校区
	@SuppressWarnings("unchecked")
	public List<SchoolRegion> getSchoolRegions(int schoolId){	 	
		Query q = this.createQuery("from SchoolRegion a where a.schoolId =:sid");
		q.setInteger("sid",schoolId);
		return  q.list(); 
	}
}
