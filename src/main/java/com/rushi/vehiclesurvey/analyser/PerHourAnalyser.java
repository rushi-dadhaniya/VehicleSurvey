package com.rushi.vehiclesurvey.analyser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rushi.vehiclesurvey.vo.Date;
import com.rushi.vehiclesurvey.vo.VehicleVO;

public class PerHourAnalyser implements Analyser {

	private Map<Integer, List<VehicleVO>> perHourVehiclesMap = new HashMap<Integer, List<VehicleVO>>();
	
	public void doAnalysis(Map<Character, List<VehicleVO>> vehicleDataMap) {

		for(Map.Entry<Character, List<VehicleVO>> entry : vehicleDataMap.entrySet()) {
			for(VehicleVO vehicle : entry.getValue()) {
				
				int hours = getHours(vehicle.getStartDate(), vehicle.getEndDate());
				List<VehicleVO> vehicleList = perHourVehiclesMap.get(hours);
				if(vehicleList != null) {
					vehicleList.add(vehicle);
				}
				else {
					vehicleList = new ArrayList<VehicleVO>();
					vehicleList.add(vehicle);
					perHourVehiclesMap.put(hours, vehicleList);
				}
				
			}
		}
		System.out.println("Per hour analysis:");
		for(Map.Entry<Integer, List<VehicleVO>> entry : perHourVehiclesMap.entrySet()) {
			System.out.print(entry.getKey() + ":" + entry.getValue().size() + "\t");
		}
		System.out.println();
		
	}

	private int getHours(Date startDate, Date endDate) {

		return max(startDate.getHours(), endDate.getHours());
		
	}

	private int max(int hours, int hours2) {
		if(hours > hours2) {
			return hours;
		}
		return hours2;
	}

}
