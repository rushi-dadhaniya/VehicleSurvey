package com.rushi.vehiclesurvey.vo;

public enum RoadBounds {

	NORTH('A', "North"),
	SOUTH('B', "South");

	private Character bound;
	String boundName;
	
	public String getBoundName() {
		return boundName;
	}

	private RoadBounds(Character bound, String boundName) {
		this.bound = bound;
		this.boundName = boundName;
	}

	public Character getBound() {
		return bound;
	}

}
