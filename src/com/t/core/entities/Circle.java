package com.t.core.entities;

import java.sql.Timestamp;

public class Circle {
	
	private Integer circleId;
	private String circleName;
	
	public Circle() {}
	
	public Circle(Integer circleId, String circleName) {
		this.circleId = circleId;
		this.circleName = circleName;
	}

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

}
