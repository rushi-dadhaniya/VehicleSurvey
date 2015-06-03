package com.rushi.vehiclesurvey.analyser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rushi.vehiclesurvey.criteria.QueryCriteria;
import com.rushi.vehiclesurvey.vo.TimeFrame;
import com.rushi.vehiclesurvey.vo.VehicleVO;
import com.rushi.vehilcesurvey.util.NumberUtil;
import com.rushi.vehilcesurvey.util.PrintQueue;

public class VehiclesAnalyser implements Analyser {

	public void doAnalysis(QueryCriteria queryCriteria,
			Map<Character, List<VehicleVO>> vehicleDataMap) {

		if (queryCriteria.getTimeFrame().equals(TimeFrame.TOTAL.getTimeFrame())) {
			totalVehiclesAnalysis(queryCriteria, vehicleDataMap);
		} else if (queryCriteria.getTimeFrame().equals(TimeFrame.PER_DAY.getTimeFrame())) {
			perDayVehicleAnalysis(queryCriteria, vehicleDataMap);
		} else {
			perHourVehicleAnalysis(queryCriteria, vehicleDataMap);
		}
	}

	private void perHourVehicleAnalysis(
			QueryCriteria queryCriteria, Map<Character, List<VehicleVO>> vehicleDataMap) {

		Map<Integer, List<VehicleVO>> perHourVehiclesMap = new HashMap<Integer, List<VehicleVO>>();		
		for (Map.Entry<Character, List<VehicleVO>> entry : vehicleDataMap.entrySet()) {
			for (VehicleVO vehicle : entry.getValue()) {

				int hours = NumberUtil.max(vehicle.getStartDate().getHours(), vehicle.getEndDate().getHours());
				List<VehicleVO> vehicles = perHourVehiclesMap.get(hours);
				if (vehicles != null) {
					vehicles.add(vehicle);
				} else {
					vehicles = new ArrayList<VehicleVO>();
					vehicles.add(vehicle);
					perHourVehiclesMap.put(hours, vehicles);
				}
			}
		}
		for (Map.Entry<Integer, List<VehicleVO>> entry : perHourVehiclesMap
				.entrySet()) {
			PrintQueue.getPrintQueue().add((entry.getKey() + ":" + entry.getValue().size() + "\t"));
		}
		PrintQueue.getPrintQueue().add("\n");
	}

	private void perDayVehicleAnalysis(QueryCriteria queryCriteria, Map<Character, List<VehicleVO>> vehicleDataMap) {

		Map<Integer, List<VehicleVO>> perDayVehiclesMap = new HashMap<Integer, List<VehicleVO>>();
		
		for (Map.Entry<Character, List<VehicleVO>> entry : vehicleDataMap.entrySet()) {
			for (VehicleVO vehicle : entry.getValue()) {
				
				int day = vehicle.getStartDate().getDay();
				List<VehicleVO> vehicles = perDayVehiclesMap.get(day);
				if (vehicles != null) {
					vehicles.add(vehicle);
				} else {
					vehicles = new ArrayList<VehicleVO>();
					vehicles.add(vehicle);
					perDayVehiclesMap.put(day, vehicles);
				}
			}
		}
		for (Map.Entry<Integer, List<VehicleVO>> entry : perDayVehiclesMap.entrySet()) {
			PrintQueue.getPrintQueue().add((entry.getKey() + ":" + entry.getValue().size() + "\t"));
		}
		PrintQueue.getPrintQueue().add("\n");
		
	}

	private void totalVehiclesAnalysis(
			QueryCriteria queryCriteria, Map<Character, List<VehicleVO>> vehicleDataMap) {
		int totalVehicles = 0;
		for (Map.Entry<Character, List<VehicleVO>> entry : vehicleDataMap
				.entrySet()) {
			totalVehicles += entry.getValue().size();
		}
		PrintQueue.getPrintQueue().add(totalVehicles + " vehicles is/are running on " + queryCriteria.getDirection() + " direction/s");
	}


}
