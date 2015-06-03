package com.rushi.vehiclesurvey.vo;

public enum AnalysisAttributes {

	ONE(1, "Vehicles"),
	TWO(2, "Speed distribution"),
	THREE(3, "Distance distribution");
	
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
