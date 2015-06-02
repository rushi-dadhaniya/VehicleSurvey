package com.rushi.vehiclesurvey.parser;


public class VehicleDataParser implements Parser {

	public Long getTimeInMillis(String vehicleReading) {
		return vehicleReading != null ? Long.parseLong(vehicleReading.substring(1)) : null;
	}

	public Character getRoadBound(String vehicleReading) {
		return vehicleReading != null ? vehicleReading.charAt(0) : null;
	}

}
