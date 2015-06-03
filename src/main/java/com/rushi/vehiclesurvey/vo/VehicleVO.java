package com.rushi.vehiclesurvey.vo;

public class VehicleVO {

	Date startDate;
	private Date endDate;
	String bounds;
	double speed;
	private Long startMillis;
	
	public Date getStartDate() {
		return startDate;
	}
	
	public void setStartDate(Date date) {
		this.startDate = date;
	}
	
	public String getBounds() {
		return bounds;
	}
	
	public void setBounds(String bounds) {
		this.bounds = bounds;
	}
	
	public double getSpeed() {
		return speed;
	}
	
	public void setSpeed(double speed) {
		this.speed = speed;
	}

	@Override
	public String toString() {
		return "VehicleVO [startDate=" + startDate + ", endDate=" + endDate
				+ ", bounds=" + bounds + ", speed=" + speed + ", startMillis="
				+ startMillis + "]";
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Long getStartMillis() {
		return startMillis;
	}

	public void setStartMillis(Long startMillis) {
		this.startMillis = startMillis;
	}
	
}
