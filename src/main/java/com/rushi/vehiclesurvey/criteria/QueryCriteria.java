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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((analysisAttribute == null) ? 0 : analysisAttribute
						.hashCode());
		result = prime * result
				+ ((direction == null) ? 0 : direction.hashCode());
		result = prime * result
				+ ((timeFrame == null) ? 0 : timeFrame.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		QueryCriteria other = (QueryCriteria) obj;
		if (analysisAttribute == null) {
			if (other.analysisAttribute != null)
				return false;
		} else if (!analysisAttribute.equals(other.analysisAttribute))
			return false;
		if (direction == null) {
			if (other.direction != null)
				return false;
		} else if (!direction.equals(other.direction))
			return false;
		if (timeFrame == null) {
			if (other.timeFrame != null)
				return false;
		} else if (!timeFrame.equals(other.timeFrame))
			return false;
		return true;
	}
	
	
	
}
