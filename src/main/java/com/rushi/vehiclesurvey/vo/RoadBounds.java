package com.rushi.vehiclesurvey.vo;

public enum RoadBounds {

	A("North bound"),
	B("South bound");
	
	String bound;
	
	private RoadBounds(String bound) {
		this.bound = bound;
	}
	
}
