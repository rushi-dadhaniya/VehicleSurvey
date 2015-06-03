package com.rushi.vehiclesurvey.analyser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rushi.vehiclesurvey.criteria.QueryCriteria;
import com.rushi.vehiclesurvey.vo.VehicleVO;
import com.rushi.vehilcesurvey.util.NumberUtil;
import com.rushi.vehilcesurvey.util.PrintQueue;

public class SpeedAnalyser extends AbstractAnalyser {

	private static final String SPEED_UNIT = "km/hr";
	
	public void totalVehiclesAnalysis(QueryCriteria queryCriteria, Map<Character, List<VehicleVO>> vehicleDataMap) {
		double speed = 0;
		int totalVehicles = 0;
		for(Map.Entry<Character, List<VehicleVO>> entry : vehicleDataMap.entrySet()) {
			for(VehicleVO vehicle : entry.getValue()) {
				totalVehicles += 1;
				speed += vehicle.getSpeed();
			}
		}
		PrintQueue.getPrintQueue().add("Speed distribution :" + NumberUtil.format(speed / totalVehicles) + SPEED_UNIT + " on " + queryCriteria.getDirection() +" direction/s");
	}

	public void perDayVehicleAnalysis(QueryCriteria queryCriteria,
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

	public void perHourVehicleAnalysis(QueryCriteria queryCriteria,
			Map<Character, List<VehicleVO>> vehicleDataMap) {
		
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
		print(perDayVehiclesMap);		
	}

	private void print(Map<Integer, List<VehicleVO>> map) {
		for (Map.Entry<Integer, List<VehicleVO>> entry : map.entrySet()) {
			double speed = 0;
			for(VehicleVO vehicle : entry.getValue()) {
				speed += vehicle.getSpeed();
			}
			PrintQueue.getPrintQueue().add((entry.getKey() + ":" + NumberUtil.format(speed / entry.getValue().size()) + SPEED_UNIT + "\t"));
		}
		PrintQueue.getPrintQueue().add("\n");
	}

	public void morningVSEvening(QueryCriteria queryCriteria,
			Map<Character, List<VehicleVO>> vehicleDataMap) {
		double morningVehiclesSpeed = 0;
		double eveningVehiclesSpeed = 0;
		int morningVehicleCounter = 0;
		int eveningVehicleCounter = 0;
		
		for(Map.Entry<Character, List<VehicleVO>> entry : vehicleDataMap.entrySet()) {
			for(VehicleVO vehicle : entry.getValue()) {
				if(isVehicleInMorningSlot(vehicle.getStartDate(), vehicle.getEndDate())) {
					morningVehiclesSpeed += vehicle.getSpeed();
					morningVehicleCounter += 1;
				}
				else {
					eveningVehiclesSpeed += vehicle.getSpeed();
					eveningVehicleCounter += 1;
				}
			}
		}
		PrintQueue.getPrintQueue().add("Morning traffic spped average:" + NumberUtil.format(morningVehiclesSpeed / morningVehicleCounter) + SPEED_UNIT + " Evening traffic average speed:" + NumberUtil.format(eveningVehiclesSpeed / eveningVehicleCounter) + " on " + queryCriteria.getDirection() +" direction");		
	}
}
