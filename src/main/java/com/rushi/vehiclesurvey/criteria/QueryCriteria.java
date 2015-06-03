package com.rushi.vehiclesurvey.criteria;

public class QueryCriteria {

	String analysisAttribute;
	String timeFrame;
	String direction;
	
	public String getAnalysisAttribute() {
		return analysisAttribute;
	}
	
	public void setAnalysisAttribute(String analysisAttribute) {
		this.analysisAttribute = analysisAttribute;
	}
	
	public String getTimeFrame() {
		return timeFrame;
	}
	
	public void setTimeFrame(String timeFrame) {
		this.timeFrame = timeFrame;
	}
	
	public String getDirection() {
		return direction;
	}
	
	public void setDirection(String direction) {
		this.direction = direction;
	}

	@Override
	public String toString() {
		return timeFrame + " " + analysisAttribute + " for " + direction + " direction/s";
		
	}
	
}
