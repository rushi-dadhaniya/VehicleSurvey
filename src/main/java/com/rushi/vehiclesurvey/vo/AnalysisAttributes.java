package com.rushi.vehiclesurvey.vo;

public enum AnalysisAttributes {

	VEHICLES(1, "Vehicles"),
	SPEED_DISTRIBUTION(2, "Speed distribution"),
	DISTANCE_DISTRIBUTION(3, "Distance distribution");
	
	int choice;
	String analysisAttribute;
	
	private AnalysisAttributes(int choice, String analysisAttribute) {
		this.choice = choice;
		this.analysisAttribute = analysisAttribute;
	}

	public int getChoice() {
		return choice;
	}

	public String getAnalysisAttribute() {
		return analysisAttribute;
	}
	
}
