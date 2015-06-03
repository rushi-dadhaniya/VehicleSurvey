package com.rushi.vehiclesurvey.vo;

public enum DirectionAttributes {

	NORTH(1, "North"),
	SOUTH(2, "South"),
	ALL(3, "All");
	
	int choice;
	String directionAttribute;
	
	private DirectionAttributes(int choice, String analysisAttribute) {
		this.choice = choice;
		this.directionAttribute = analysisAttribute;
	}

	public int getChoice() {
		return choice;
	}

	public String getDirectionAttribute() {
		return directionAttribute;
	}
	
}
