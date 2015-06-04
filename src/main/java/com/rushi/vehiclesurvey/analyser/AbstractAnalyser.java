package com.rushi.vehiclesurvey.analyser;

import java.util.List;
import java.util.Map;

import com.rushi.vehiclesurvey.criteria.QueryCriteria;
import com.rushi.vehiclesurvey.vo.Date;
import com.rushi.vehiclesurvey.vo.TimeFrame;
import com.rushi.vehiclesurvey.vo.VehicleVO;
import com.rushi.vehilcesurvey.util.NumberUtil;
import com.rushi.vehilcesurvey.util.TimeUtil;

public abstract class AbstractAnalyser {

	private static final int MORNING_STARTING_HOURS = 0;
	private static final int MORNING_END_HOURS = 16;
	
	public void doAnalysis(QueryCriteria queryCriteria, Map<Character, List<VehicleVO>> vehicleDataMap) {
		if (queryCriteria.getTimeFrame().equals(TimeFrame.TOTAL.getTimeFrame())) {
			totalVehiclesAnalysis(queryCriteria, vehicleDataMap);
		} else if (queryCriteria.getTimeFrame().equals(TimeFrame.PER_DAY.getTimeFrame())) {
			perDayVehicleAnalysis(queryCriteria, vehicleDataMap);
		} else if (queryCriteria.getTimeFrame().equals(TimeFrame.MORNING_EVENING.getTimeFrame())) {
			morningVSEvening(queryCriteria, vehicleDataMap);
		} else {
			perHourVehicleAnalysis(queryCriteria, vehicleDataMap);
		}
	}
	
	protected boolean isVehicleInMorningSlot(Date startDate, Date endDate) {
		int hours = NumberUtil.max(startDate.getHours(), endDate.getHours());
		if(hours >= MORNING_STARTING_HOURS && hours <= MORNING_END_HOURS) {
			return true;
		}
		return false;
	}
	
	protected double calculateDistance(VehicleVO vehicle1, VehicleVO vehicle2) {
		
		TimeUtil timeUtil = new TimeUtil();
		double speed = (vehicle1.getSpeed() + vehicle2.getSpeed()) / 2;
		Double hours1 = timeUtil.convertMilliSecondsToHours(vehicle1.getStartMillis());
		Double hours2 = timeUtil.convertMilliSecondsToHours(vehicle1.getStartMillis());
		Double time = (hours1 + hours2) / 2;
		return speed * time;
		
	}
	
	abstract void totalVehiclesAnalysis(QueryCriteria queryCriteria, Map<Character, List<VehicleVO>> vehicleDataMap);
	
	abstract void perDayVehicleAnalysis(QueryCriteria queryCriteria, Map<Character, List<VehicleVO>> vehicleDataMap);
	
	abstract void perHourVehicleAnalysis(QueryCriteria queryCriteria, Map<Character, List<VehicleVO>> vehicleDataMap);
	
	abstract void morningVSEvening(QueryCriteria queryCriteria, Map<Character, List<VehicleVO>> vehicleDataMap);
	
}
