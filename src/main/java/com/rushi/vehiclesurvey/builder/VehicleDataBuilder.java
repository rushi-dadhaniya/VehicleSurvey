package com.rushi.vehiclesurvey.builder;

import java.util.List;
import java.util.Map;

import com.rushi.vehiclesurvey.parser.VehicleDataParser;
import com.rushi.vehiclesurvey.vo.Date;
import com.rushi.vehilcesurvey.util.TimeUtil;
import com.rushi.vehilcesurvey.util.VehicleData;


public class VehicleDataBuilder {

	public void build(List<String> lines) {
		
		Map<Character, List<Date>> vehicleBoundDateListMap = VehicleData.getInstance();
		
		TimeUtil timeUtil = new TimeUtil();
		
		Long previousMillis = new Long(0);
		int currentDay = 1;
		VehicleDataParser vehicleDataParser = new VehicleDataParser();
		
		for(String line : lines) {
			Long currentTime = vehicleDataParser.getTimeInMillis(line);
			Date date = timeUtil.convertMilliSecondsToDate(previousMillis, currentTime, currentDay);
			previousMillis = currentTime;
			currentDay = date.getDay();
			Character roadBound = vehicleDataParser.getRoadBound(line);
			vehicleBoundDateListMap.get(roadBound).add(date);
			
		}
		
	}
}
