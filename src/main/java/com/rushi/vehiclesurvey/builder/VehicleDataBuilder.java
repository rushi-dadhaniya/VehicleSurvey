package com.rushi.vehiclesurvey.builder;

import java.util.List;
import java.util.Map;

import com.rushi.vehiclesurvey.parser.VehicleDataParser;
import com.rushi.vehiclesurvey.vo.Date;
import com.rushi.vehilcesurvey.util.TimeUtil;
import com.rushi.vehilcesurvey.util.VehicleDataFactory;


public class VehicleDataBuilder {

	public void build(List<String> vehicleReadings) {
		
		TimeUtil timeUtil = new TimeUtil();
		VehicleDataParser vehicleDataParser = new VehicleDataParser();
		
		Map<Character, List<Date>> vehicleBoundDateListMap = VehicleDataFactory.getInstance();
		Long previousMillis = new Long(0);
		int currentDay = 1;
		
		for(String vehicleReading : vehicleReadings) {
			Long currentTime = vehicleDataParser.getTimeInMillis(vehicleReading);
			Date date = timeUtil.convertMilliSecondsToDate(previousMillis, currentTime, currentDay);
			previousMillis = currentTime;
			currentDay = date.getDay();
			Character roadBound = vehicleDataParser.getRoadBound(vehicleReading);
			vehicleBoundDateListMap.get(roadBound).add(date);
		}
	}
	
}
