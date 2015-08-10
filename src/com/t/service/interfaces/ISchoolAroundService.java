package com.t.service.interfaces;

import com.t.bean.SchoolAroundItemBean;
import com.t.core.entities.SchoolAroundItem;

import java.util.List;

public interface ISchoolAroundService {
	
	 //添加一个校园周边的记录
	 public void addItem(SchoolAroundItemBean bean);
	 
	 //更新商铺
	 public void updateItem (SchoolAroundItem item);
	 
	 //获得周边所有的商铺
	 public List<SchoolAroundItem> getAllShops();
	 
	 //前台综合搜索
	 public List<SchoolAroundItem> IntegratedSearchAll(String itemId,String itemName,String itemLocation);
	 
	 //获得一个记录
	 public SchoolAroundItemBean getItem(Integer itemId);
	 
	 //获得一个item记录
	 public SchoolAroundItem finById(int itemId);
	 
	 //获得校园周边某个Object下的列表：地铁5号线 ，地铁10号线......
		public List<SchoolAroundItemBean> getList(Integer typeId,Integer objectId,Integer pageId, Integer pageSize);
	 
}
