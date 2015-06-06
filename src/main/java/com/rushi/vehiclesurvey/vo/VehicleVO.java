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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bounds == null) ? 0 : bounds.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		long temp;
		temp = Double.doubleToLongBits(speed);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result
				+ ((startMillis == null) ? 0 : startMillis.hashCode());
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
		VehicleVO other = (VehicleVO) obj;
		if (bounds == null) {
			if (other.bounds != null)
				return false;
		} else if (!bounds.equals(other.bounds))
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (Double.doubleToLongBits(speed) != Double
				.doubleToLongBits(other.speed))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (startMillis == null) {
			if (other.startMillis != null)
				return false;
		} else if (!startMillis.equals(other.startMillis))
			return false;
		return true;
	}
	
}
