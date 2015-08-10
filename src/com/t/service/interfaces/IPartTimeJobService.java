package com.t.service.interfaces;

import java.util.List;

import com.t.bean.PartTimeJobBean;
import com.t.bean.PartTimeJobDetailBean;
import com.t.core.entities.PartTimeJob;

public interface IPartTimeJobService {
     public void add(PartTimeJob job);//添加
     public List<PartTimeJobBean> getList(Integer jobtype,Integer pageId,Integer pageSize);//获得职位列表
     //public PartTimeJobDetailBean getDetails(int jobId); //获得某个职位详情 
     public PartTimeJob getDetails(int jobId); //获得某个职位详情 
}
