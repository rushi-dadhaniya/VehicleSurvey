package com.rushi.vehilcesurvey.util;

import java.util.HashMap;
import java.util.Map;

public class VehicleData {

	private static Map<Character, Long> vehicleBoundTimeMap = new HashMap<Character, Long>();
	
	public static Map<Character, Long> getInstance() {
		return vehicleBoundTimeMap;
	}
	
}
