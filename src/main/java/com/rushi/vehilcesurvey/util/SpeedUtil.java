package com.rushi.vehilcesurvey.util;

public class SpeedUtil {

	private static final Double DISTANCE = 2.5; 
	
	public double calculateSpeed(double time) {
		return DISTANCE / time;
	}
	
}
