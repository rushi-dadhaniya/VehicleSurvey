package com.rushi.vehiclesurvey.parser;

import com.rushi.vehilcesurvey.util.VehicleData;

public class VehicleDataParser implements Parser {

	public void parse(String line) {
		Character roadBound = line.charAt(0);
		Long time = Long.parseLong(line.substring(1));
		VehicleData.getInstance().put(roadBound, time);
	}

}
