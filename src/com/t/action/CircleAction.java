package com.t.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.t.core.entities.Circle;
import com.t.service.interfaces.ICircleService;
import com.t.utils.BaseAction;

public class CircleAction extends BaseAction {
	
	private static final long serialVersionUID = 1L;
	private Integer circleId;
	private String circleName;
	private List<Circle> circleInfos;

	@Autowired
	private ICircleService circleService;

	public Integer getCircleId() {
		return circleId;
	}

	public void setCircleId(Integer circleId) {
		this.circleId = circleId;
	}

	public String getCircleName() {
		return circleName;
	}

	public void setCircleName(String circleName) {
		this.circleName = circleName;
	}
	
	//获取商圈列表
	public String fetchCircleInfos(){
		try{
			circleInfos = circleService.fetchCircleInfo();
		}catch(NullPointerException e){
			result.put(STATE, FAIL);
			return SUCCESS;
		}
		result.put("results", circleInfos);
		result.put(STATE,SUCCESS);
		return SUCCESS;
	}

}
