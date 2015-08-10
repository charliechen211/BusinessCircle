package com.t.service.interfaces;

import java.util.List;

import com.t.bean.BadInteractionsBean;
import com.t.bean.InteractionBean;
import com.t.core.entities.Interaction;

public interface IInteractionService {
     public int addInteraction(Interaction ele);//添加
     public void delInteractionById(int id);//删除
     public List<InteractionBean> getList(int pageId,int pageSize,int schoolId,int userId); //获得结果集
     public void reportBad(int topicId);//举报
     public List<BadInteractionsBean> getBadNews();//获得被举报超过一定次数的贴子，让前台审核
     public void passInteraction(int topicId);  //审核通过
     
}
