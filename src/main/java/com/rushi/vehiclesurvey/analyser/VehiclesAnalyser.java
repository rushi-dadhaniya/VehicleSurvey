package com.rushi.vehiclesurvey.analyser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rushi.vehiclesurvey.criteria.QueryCriteria;
import com.rushi.vehiclesurvey.vo.Date;
import com.rushi.vehiclesurvey.vo.VehicleVO;
import com.rushi.vehilcesurvey.util.NumberUtil;
import com.rushi.vehilcesurvey.util.PrintQueue;

public class VehiclesAnalyser extends AbstractAnalyser {

	private static final int MORNING_STARTING_HOURS = 0;
	private static final int MORNING_END_HOURS = 16;
	
	public void morningVSEvening(QueryCriteria queryCriteria, Map<Character, List<VehicleVO>> vehicleDataMap) {
		
		List<VehicleVO> morningVehicles = new ArrayList<VehicleVO>();
		List<VehicleVO> eveningVehicles = new ArrayList<VehicleVO>();
		
		for(Map.Entry<Character, List<VehicleVO>> entry : vehicleDataMap.entrySet()) {
			for(VehicleVO vehicle : entry.getValue()) {
				if(isVehicleInMorningSlot(vehicle.getStartDate(), vehicle.getEndDate())) {
					morningVehicles.add(vehicle);
				}
				else {
					eveningVehicles.add(vehicle);
				}
			}
		}
		PrintQueue.getPrintQueue().add("Morning traffic:" + morningVehicles.size() + " Evening traffic:" + eveningVehicles.size() + " on " + queryCriteria.getDirection() +" direction");
	}
	
	
	public void perHourVehicleAnalysis(QueryCriteria queryCriteria, Map<Character, List<VehicleVO>> vehicleDataMap) {

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

	public void perDayVehicleAnalysis(QueryCriteria queryCriteria, Map<Character, List<VehicleVO>> vehicleDataMap) {

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

	public void totalVehiclesAnalysis(
			QueryCriteria queryCriteria, Map<Character, List<VehicleVO>> vehicleDataMap) {
		int totalVehicles = 0;
		for (Map.Entry<Character, List<VehicleVO>> entry : vehicleDataMap
				.entrySet()) {
			totalVehicles += entry.getValue().size();
		}
		PrintQueue.getPrintQueue().add(totalVehicles + " vehicles is/are running on " + queryCriteria.getDirection() + " direction/s");
	}

	private boolean isVehicleInMorningSlot(Date startDate, Date endDate) {
		int hours = NumberUtil.max(startDate.getHours(), endDate.getHours());
		if(hours >= MORNING_STARTING_HOURS && hours <= MORNING_END_HOURS) {
			return true;
		}
		return false;
	}

}
