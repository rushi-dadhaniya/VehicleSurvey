package com.rushi.vehiclesurvey.analyser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.rushi.vehiclesurvey.vo.Date;
import com.rushi.vehiclesurvey.vo.VehicleVO;

public class MorningEveningTrafficAnalyser implements Analyser{

	private static final int MORNING_STARTING_HOURS = 0;
	private static final int MORNING_END_HOURS = 16;
	
	private List<VehicleVO> morningVehicles;
	private List<VehicleVO> eveningVehicles;
	
	public void doAnalysis(Map<Character, List<VehicleVO>> vehicleDataMap) {
		
		morningVehicles = new ArrayList<VehicleVO>();
		eveningVehicles = new ArrayList<VehicleVO>();
		
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
		System.out.println("Mornign vechicles: " + morningVehicles.size() + " Evening vehicles:"+ eveningVehicles.size());
		
	}

	private boolean isVehicleInMorningSlot(Date startDate, Date endDate) {
		int hours = max(startDate.getHours(), endDate.getHours());
		if(hours >= MORNING_STARTING_HOURS && hours <= MORNING_END_HOURS) {
			return true;
		}
		return false;
	}

	private int max(int hours, int hours2) {
		if(hours > hours2) {
			return hours;
		}
		return hours2;
	}
	
	
}
