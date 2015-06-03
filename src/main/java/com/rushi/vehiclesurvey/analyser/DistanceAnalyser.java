package com.rushi.vehiclesurvey.analyser;

import java.util.List;
import java.util.Map;

import com.rushi.vehiclesurvey.vo.VehicleVO;

public class DistanceAnalyser implements Analyser {

	public void doAnalysis(Map<Character, List<VehicleVO>> vehicleDataMap) {

		for(Map.Entry<Character, List<VehicleVO>> entry : vehicleDataMap.entrySet()) {
			List<VehicleVO> vehicles = entry.getValue();
			double distance = 0;
			int vehiclesCount = 0;
			int day = 0;
			for(int vehicleIterator = 0 ; vehicleIterator < vehicles.size() - 1 ; vehicleIterator++) {
				
				VehicleVO vehicle1 = vehicles.get(vehicleIterator);
				VehicleVO vehicle2 = vehicles.get(vehicleIterator + 1);
				if(vehicle1.getStartDate().getDay() == vehicle2.getStartDate().getDay()) {
					vehiclesCount += 1;
					distance += calculateDistance(vehicles.get(vehicleIterator), vehicles.get(vehicleIterator + 1));
					day = vehicle1.getStartDate().getDay();
				}
				else {
					if(distance > 0) {
						System.out.println("distance between vehicles" + distance / vehiclesCount + " on day " + day);
					}
					else {
						distance = 0;
						vehiclesCount = 0;
						day = 0;
					}
				}
				
			}
		}
		
	}

	private double calculateDistance(VehicleVO vehicle1, VehicleVO vehicle2) {
		
		double speed = (vehicle1.getSpeed() + vehicle2.getSpeed()) / 2;
		long time = (vehicle1.getStartMillis() + vehicle2.getStartMillis()) / 2;
		return speed * time;
		
	}

}

