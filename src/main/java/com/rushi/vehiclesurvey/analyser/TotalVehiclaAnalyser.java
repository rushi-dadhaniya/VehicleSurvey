package com.rushi.vehiclesurvey.analyser;

import java.util.List;
import java.util.Map;

import com.rushi.vehiclesurvey.vo.RoadBounds;
import com.rushi.vehiclesurvey.vo.VehicleVO;
import com.rushi.vehilcesurvey.util.PrintQueue;

public class TotalVehiclaAnalyser implements Analyse {

	public void doAnalysis(Map<Character, List<VehicleVO>> vehicleDataMap) {

		int totalVehicles = 0;
		
		for(Map.Entry<Character,List<VehicleVO>> boundEntry : vehicleDataMap.entrySet()) {
			totalVehicles += boundEntry.getValue().size();
		}
		PrintQueue.getPrintQueue().add("Total vehicles running on bounds" +getBoundNames()  + ":" + totalVehicles);
		
	}
	
	private String getBoundNames() {
		String boundNames = " ";
		for(RoadBounds roadBound : RoadBounds.values()) {
			boundNames += roadBound.getBound().toString() + ",";
		}
		return boundNames;
	}

}
