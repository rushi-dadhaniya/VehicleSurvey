package com.rushi.vehiclesurvey.validator;


public class VehicleReadingValidator implements Validator {

	private static final String VEHICLE_READING_PATTERN = "^[A|B]([0-9]+)";
	
	public boolean isValid(String vehicleReading) {
		if(vehicleReading != null) {
			return vehicleReading.matches(VEHICLE_READING_PATTERN);
		}
		return false;
	}

}
