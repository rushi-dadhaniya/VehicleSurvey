package com.rushi.vehiclesurvey.analyser;

import java.util.List;
import java.util.Map;

import com.rushi.vehiclesurvey.criteria.QueryCriteria;
import com.rushi.vehiclesurvey.vo.TimeFrame;
import com.rushi.vehiclesurvey.vo.VehicleVO;

public abstract class AbstractAnalyser {

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
	
	abstract void totalVehiclesAnalysis(QueryCriteria queryCriteria, Map<Character, List<VehicleVO>> vehicleDataMap);
	
	abstract void perDayVehicleAnalysis(QueryCriteria queryCriteria, Map<Character, List<VehicleVO>> vehicleDataMap);
	
	abstract void perHourVehicleAnalysis(QueryCriteria queryCriteria, Map<Character, List<VehicleVO>> vehicleDataMap);
	
	abstract void morningVSEvening(QueryCriteria queryCriteria, Map<Character, List<VehicleVO>> vehicleDataMap);
	
}
