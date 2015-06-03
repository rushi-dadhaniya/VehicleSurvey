package com.rushi.vehiclesurvey.vo;

public enum TimeFrame {

	ONE(1, "Total"),
	TWO(2, "Per day"),
	THREE(3, "Per hour");
	
	int choice;
	String timeFrame;
	
	private TimeFrame(int choice, String analysisAttribute) {
		this.choice = choice;
		this.timeFrame = analysisAttribute;
	}

	public int getChoice() {
		return choice;
	}

	public String getAnalysisAttribute() {
		return timeFrame;
	}
	
}
