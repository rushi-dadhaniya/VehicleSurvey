package com.rushi.vehiclesurvey.vo;

public enum TimeFrame {

	TOTAL(1, "Total"),
	PER_DAY(2, "Per day"),
	PER_HOUR(3, "Per hour"),
	MORNING_EVENING(4, "Morning vs Evening");
	
	int choice;
	String timeFrame;
	
	private TimeFrame(int choice, String analysisAttribute) {
		this.choice = choice;
		this.timeFrame = analysisAttribute;
	}

	public int getChoice() {
		return choice;
	}

	public String getTimeFrame() {
		return timeFrame;
	}
	
}
