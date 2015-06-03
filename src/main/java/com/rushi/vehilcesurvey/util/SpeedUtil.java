package com.rushi.vehilcesurvey.util;

public class SpeedUtil {

	private static final Double DISTANCE = 0.0025; 
	
	public double calculateSpeed(double time) {
		return DISTANCE / time;
	}
	
}
