package com.rushi.vehiclesurvey.vo;

public enum DirectionAttributes {

	ONE(1, "North"),
	TWO(2, "South"),
	THREE(3, "All");
	
	int choice;
	String directionAttribute;
	
	private DirectionAttributes(int choice, String analysisAttribute) {
		this.choice = choice;
		this.directionAttribute = analysisAttribute;
	}

	public int getChoice() {
		return choice;
	}

	public String getAnalysisAttribute() {
		return directionAttribute;
	}
	
}
