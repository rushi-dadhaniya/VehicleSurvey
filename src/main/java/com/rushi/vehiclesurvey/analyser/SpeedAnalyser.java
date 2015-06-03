package com.rushi.vehiclesurvey.analyser;

import java.util.List;
import java.util.Map;

import com.rushi.vehiclesurvey.vo.VehicleVO;
import com.rushi.vehilcesurvey.util.PrintQueue;

public class SpeedAnalyser implements Analyser {

	public void doAnalysis(Map<Character, List<VehicleVO>> vehicleDataMap) {

		double speed = 0;
		int totalVehicles = 0;
		for(Map.Entry<Character, List<VehicleVO>> entry : vehicleDataMap.entrySet()) {
			for(VehicleVO vehicle : entry.getValue()) {
				totalVehicles += 1;
				speed += vehicle.getSpeed();
			}
		}
		PrintQueue.getPrintQueue().add("Speed distribution :" + speed / totalVehicles);
	}
}
