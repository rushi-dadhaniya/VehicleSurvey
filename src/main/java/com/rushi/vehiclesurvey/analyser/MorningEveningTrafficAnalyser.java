package com.rushi.vehiclesurvey.analyser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
				if(vehicle.getStartDate().getHours() >= MORNING_STARTING_HOURS && vehicle.getStartDate().getHours() <= MORNING_END_HOURS) {
					morningVehicles.add(vehicle);
				}
				else {
					eveningVehicles.add(vehicle);
				}
			}
		}
		System.out.println("Mornign vechicles: " + morningVehicles.size() + " Evening vehicles:"+ eveningVehicles.size());
		
	}

	
	
}
