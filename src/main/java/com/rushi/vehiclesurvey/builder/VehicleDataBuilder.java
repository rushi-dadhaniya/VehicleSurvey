package com.rushi.vehiclesurvey.builder;

import java.util.ArrayList;
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

	private VehicleDataParser vehicleDataParser;
	private TimeUtil timeUtil;
	private SpeedUtil speedUtil;
	private static Date date;
	public void build(List<String> vehicleReadings) {
		
		if(vehicleReadings != null) {
			setTimeUtil(new TimeUtil());
			setSpeedUtil(new SpeedUtil());
			setVehicleDataParser(new VehicleDataParser());
			Map<Character, List<VehicleVO>> vehicleBoundDateListMap = VehicleDataFactory.getInstance();
			
			int currentDay = 1;
			int numberOfBounds = getNumberOfBounds();
			Character previousRoadBound = vehicleDataParser.getRoadBound(vehicleReadings.get(0));
			Long previousTime = vehicleDataParser.getTimeInMillis(vehicleReadings.get(0));
			Date startDate = timeUtil.convertMilliSecondsToDate(new Long(0), previousTime, currentDay);
			for(int vehicleReadingIterator = 1 ; vehicleReadingIterator < vehicleReadings.size(); vehicleReadingIterator++) {
				
				String vehicleReading = vehicleReadings.get(vehicleReadingIterator);
				
				Long currentTime = vehicleDataParser.getTimeInMillis(vehicleReading);
				date = timeUtil.convertMilliSecondsToDate(previousTime, currentTime, currentDay);
				Character currentRoadBound = vehicleDataParser.getRoadBound(vehicleReading);
				double speed = 0;
				
				if(isVehiclePossible(numberOfBounds, vehicleReadingIterator)) {
					if(currentRoadBound == previousRoadBound) {
						speed = speedUtil.calculateSpeed(timeUtil.convertMilliSecondsToHours(currentTime - previousTime));
						buildVehicleData(currentRoadBound.toString(), previousTime, startDate, date, vehicleBoundDateListMap, speed);
						vehicleReadingIterator += 1;
					}
					else {
						List<Long> boundTimes = iterateTillLastAxel(previousTime, currentTime, currentDay, vehicleReadings, vehicleReadingIterator);
						speed = calculateAverageSpeed(boundTimes);
						buildVehicleData(currentRoadBound.toString(),previousTime, startDate, date, vehicleBoundDateListMap, speed);
						vehicleReadingIterator += getNumberOfBounds() + 1;
					}
					if(vehicleReadingIterator < vehicleReadings.size()) {
						previousTime = vehicleDataParser.getTimeInMillis(vehicleReadings.get(vehicleReadingIterator));
						if(timeUtil.isNewDay(currentTime, previousTime)) {
							currentDay += 1;
						}
						previousRoadBound = vehicleDataParser.getRoadBound(vehicleReadings.get(vehicleReadingIterator));
						startDate = timeUtil.convertMilliSecondsToDate(new Long(0), previousTime, currentDay);
					}
				}
				
				else {
					previousTime = currentTime;
					previousRoadBound = currentRoadBound;
					currentDay = date.getDay();
				}
			}
		}
		
	}
	
	private double calculateAverageSpeed(List<Long> boundTimes) {

		double speed = 0;
		for(Long boundTime : boundTimes) {
			speed += speedUtil.calculateSpeed(timeUtil.convertMilliSecondsToHours(boundTime));
		}
		return speed / boundTimes.size();
	}

	private List<Long> iterateTillLastAxel(Long previousTime, Long currentTime, int currentDay, List<String> vehicleReadings, int vehicleReadingIterator) {
	
		int numberOfBounds = getNumberOfBounds();
		List<Long> boundTimes = new ArrayList<Long>();
		boundTimes.add(previousTime);
		boundTimes.add(currentTime);
		
		for(int iterator = 0 ; iterator < numberOfBounds ; iterator++) {
			vehicleReadingIterator += 1;
			if(vehicleReadingIterator < vehicleReadings.size()) {
				Long boundTime = vehicleDataParser.getTimeInMillis(vehicleReadings.get(vehicleReadingIterator));
				currentTime = boundTime;
				date = timeUtil.convertMilliSecondsToDate(previousTime, currentTime, currentDay);
				previousTime = boundTime;
				int index = 0;
				if(iterator % numberOfBounds != 0) {
					index = 1;
				}
				boundTimes.set(index, boundTime - boundTimes.get(index));
			}
		}
		return boundTimes;
	}

	private boolean isVehiclePossible(int numberOfBounds, int vehicleReadingIterator) {
		return (vehicleReadingIterator + 1) % numberOfBounds == 0;
	}

	private int getNumberOfBounds() {
		return RoadBounds.values().length;
	}

	private void buildVehicleData(String currentRoadBound,Long previousTime, Date startDate, Date endDate, Map<Character, List<VehicleVO>> vehicleBoundDateListMap, double speed) {
		
		if(vehicleBoundDateListMap.get(currentRoadBound.charAt(0)) != null && speed != 0) {
			VehicleVO vehicleVO = new VehicleVO();
			vehicleVO.setBounds(currentRoadBound.toString());
			vehicleVO.setStartDate(startDate);
			vehicleVO.setEndDate(endDate);
			vehicleVO.setSpeed(speed);
			vehicleVO.setStartMillis(previousTime);
			vehicleBoundDateListMap.get(currentRoadBound.charAt(0)).add(vehicleVO);
		}
	}
	
	public void setVehicleDataParser(VehicleDataParser vehicleDataParser) {
		this.vehicleDataParser = vehicleDataParser;
	}

	public void setTimeUtil(TimeUtil timeUtil) {
		this.timeUtil = timeUtil;
	}

	public void setSpeedUtil(SpeedUtil speedUtil) {
		this.speedUtil = speedUtil;
	}
	
}
