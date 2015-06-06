package com.rushi.vehiclesurvey.util;

public class SpeedUtil {

	private static final Double DISTANCE = 0.0025; 
	
	public static double calculateSpeed(double time) {
		if(time > 0.0) {
			return DISTANCE / time;
		}
		return 0.0;
	}
	
}
