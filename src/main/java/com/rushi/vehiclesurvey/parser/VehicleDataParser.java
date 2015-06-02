package com.rushi.vehiclesurvey.parser;


public class VehicleDataParser implements Parser {

	public Long getTimeInMillis(String line) {
		return line != null ? Long.parseLong(line.substring(1)) : null;
	}

	public Character getRoadBound(String line) {
		return line != null ? line.charAt(0) : null;
	}

}
