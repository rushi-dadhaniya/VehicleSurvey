package com.rushi.vehiclesurvey.analyser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rushi.vehiclesurvey.criteria.QueryCriteria;
import com.rushi.vehiclesurvey.util.NumberUtil;
import com.rushi.vehiclesurvey.util.PrintQueue;
import com.rushi.vehiclesurvey.vo.VehicleVO;

public class PickTimeAnalyser extends AbstractAnalyser {

	@Override
	void totalVehiclesAnalysis(QueryCriteria queryCriteria,
			Map<Character, List<VehicleVO>> vehicleDataMap) {

	}

	@Override
	void perDayVehicleAnalysis(QueryCriteria queryCriteria,
			Map<Character, List<VehicleVO>> vehicleDataMap) {
		
		Map<Integer, List<VehicleVO>> perDayPickTimeVehicleMap = new HashMap<Integer, List<VehicleVO>>();
		Map<Integer, List<VehicleVO>> perDayVehicleDataMap = getPerDayVehicleDataMap(queryCriteria, vehicleDataMap);
		for(Map.Entry<Integer, List<VehicleVO>> entry : perDayVehicleDataMap.entrySet()) {
			for (VehicleVO vehicle : entry.getValue()) {

				int hours = NumberUtil.max(vehicle.getStartDate().getHours(), vehicle.getEndDate().getHours());
				List<VehicleVO> vehicles = perDayPickTimeVehicleMap.get(hours);
				if (vehicles != null) {
					vehicles.add(vehicle);
				} else {
					vehicles = new ArrayList<VehicleVO>();
					vehicles.add(vehicle);
					perDayPickTimeVehicleMap.put(hours, vehicles);
				}
			}
		}
		
	}

	private Map<Integer, List<VehicleVO>> getPerDayVehicleDataMap(QueryCriteria queryCriteria,
			Map<Character, List<VehicleVO>> vehicleDataMap) {
		Map<Integer, 
		List<VehicleVO>> perDayVehiclesMap = new HashMap<Integer, List<VehicleVO>>();
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
		return perDayVehiclesMap;
	}

	@Override
	void perHourVehicleAnalysis(QueryCriteria queryCriteria,
			Map<Character, List<VehicleVO>> vehicleDataMap) {

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
		print(perHourVehiclesMap);
		
	}

	private void print(Map<Integer, List<VehicleVO>> map) {

		int maxVehicles = 0;
		int pickTime = 0;
		for(Map.Entry<Integer, List<VehicleVO>> entry : map.entrySet()) {
			if(entry.getValue().size() > maxVehicles) {
				maxVehicles = entry.getValue().size();
				pickTime = entry.getKey();
			}
		}
		PrintQueue.getPrintQueue().add("Pick traffic was at hour:" + pickTime + " vehicles:" + maxVehicles);
	}

	@Override
	void morningVSEvening(QueryCriteria queryCriteria,
			Map<Character, List<VehicleVO>> vehicleDataMap) {

	}

}
