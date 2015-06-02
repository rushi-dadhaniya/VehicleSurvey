package com.rushi.vehiclesurvey.builder;

import java.util.List;
import java.util.Map;

import com.rushi.vehiclesurvey.parser.VehicleDataParser;
import com.rushi.vehiclesurvey.vo.Date;
import com.rushi.vehiclesurvey.vo.RoadBounds;
import com.rushi.vehiclesurvey.vo.VehicleVO;
import com.rushi.vehilcesurvey.util.SpeedUtil;
import com.rushi.vehilcesurvey.util.TimeUtil;
import com.rushi.vehilcesurvey.util.VehicleDataFactory;


public class VehicleDataBuilder {

	public void build(List<String> vehicleReadings) {
		
		TimeUtil timeUtil = new TimeUtil();
		VehicleDataParser vehicleDataParser = new VehicleDataParser();
		SpeedUtil speedUtil = new SpeedUtil();
		
		Map<Character, List<VehicleVO>> vehicleBoundDateListMap = VehicleDataFactory.getInstance();
		int currentDay = 1;
		int numberOfBounds = RoadBounds.values().length;
		Character previousRoadBound = vehicleDataParser.getRoadBound(vehicleReadings.get(0));
		Long previousTime = vehicleDataParser.getTimeInMillis(vehicleReadings.get(0));
		Date startDate = timeUtil.convertMilliSecondsToDate(new Long(0), previousTime, currentDay);
		for(int vehicleReadingIterator = 1 ; vehicleReadingIterator < vehicleReadings.size(); vehicleReadingIterator++) {
			
			String vehicleReading = vehicleReadings.get(vehicleReadingIterator);
			
			Long currentTime = vehicleDataParser.getTimeInMillis(vehicleReading);
			Date date = timeUtil.convertMilliSecondsToDate(previousTime, currentTime, currentDay);
			Character currentRoadBound = vehicleDataParser.getRoadBound(vehicleReading);
			double speed = 0;
			
			if((vehicleReadingIterator + 1) % numberOfBounds == 0) {
				if(currentRoadBound == previousRoadBound) {
					speed = speedUtil.calculateSpeed(timeUtil.convertMilliSecondsToMinutes(currentTime - previousTime));
					buildVehicleDataOnSameRoadBound(currentRoadBound, startDate, date, vehicleBoundDateListMap, speed);
					vehicleReadingIterator += 1;
					if(vehicleReadingIterator < vehicleReadings.size()) {
						previousTime = vehicleDataParser.getTimeInMillis(vehicleReadings.get(vehicleReadingIterator));
						if(previousTime < currentTime) {
							currentDay += 1;
						}
						previousRoadBound = vehicleDataParser.getRoadBound(vehicleReadings.get(vehicleReadingIterator));
						startDate = timeUtil.convertMilliSecondsToDate(new Long(0), previousTime, currentDay);
					}
				}
				else {
					long oneBoundTime = previousTime;
					long secondBoundTime = currentTime;
					for(int i = 0 ; i < numberOfBounds ; i++) {
						vehicleReadingIterator += 1;
						
						if(i % numberOfBounds == 0) {
							oneBoundTime = vehicleDataParser.getTimeInMillis(vehicleReadings.get(vehicleReadingIterator)) - oneBoundTime;
						}
						else {
							secondBoundTime = vehicleDataParser.getTimeInMillis(vehicleReadings.get(vehicleReadingIterator)) - secondBoundTime;
						}
						
					}
					
					double speed1 = speedUtil.calculateSpeed(timeUtil.convertMilliSecondsToMinutes(oneBoundTime));
					double speed2 = speedUtil.calculateSpeed(timeUtil.convertMilliSecondsToMinutes(secondBoundTime));
					speed = (speed1 + speed2) / 2;
					buildVehicleDataOnDifferentBounds(currentRoadBound, previousRoadBound, startDate, date, vehicleBoundDateListMap, speed);
					vehicleReadingIterator += 1;
					if(vehicleReadingIterator < vehicleReadings.size()) {
						previousTime = vehicleDataParser.getTimeInMillis(vehicleReadings.get(vehicleReadingIterator));
						if(previousTime < currentTime) {
							currentDay += 1;
						}
						previousRoadBound = vehicleDataParser.getRoadBound(vehicleReadings.get(vehicleReadingIterator));
						startDate = timeUtil.convertMilliSecondsToDate(new Long(0), previousTime, currentDay);
					}
					
				}
			}
			
			else {
				previousTime = currentTime;
				previousRoadBound = currentRoadBound;
				currentDay = date.getDay();
			}
		}
		
	}

	private void buildVehicleDataOnSameRoadBound(Character currentRoadBound, Date startDate, Date endDate, Map<Character, List<VehicleVO>> vehicleBoundDateListMap, double speed) {
			if(vehicleBoundDateListMap.get(currentRoadBound) != null && speed != 0) {
			
			VehicleVO vehicleVO = new VehicleVO();
			vehicleVO.setBounds(currentRoadBound.toString());
			vehicleVO.setStartDate(startDate);
			vehicleVO.setEndDate(endDate);
			vehicleVO.setSpeed(speed);
			vehicleBoundDateListMap.get(currentRoadBound).add(vehicleVO);
		}
	}

	private void buildVehicleDataOnDifferentBounds(Character currentRoadBound, Character previousRoadBound, Date startDate, Date endDate, Map<Character, List<VehicleVO>> vehicleBoundDateListMap, double speed) {
		
		if(vehicleBoundDateListMap.get(previousRoadBound) != null && speed != 0) {
			VehicleVO vehicleVO = new VehicleVO();
			vehicleVO.setBounds(currentRoadBound.toString() + " " + previousRoadBound.toString() );
			vehicleVO.setStartDate(startDate);
			vehicleVO.setEndDate(endDate);
			vehicleVO.setSpeed(speed);
			vehicleBoundDateListMap.get(currentRoadBound).add(vehicleVO);
			vehicleBoundDateListMap.get(previousRoadBound).add(vehicleVO);
		}
	}
	
}
