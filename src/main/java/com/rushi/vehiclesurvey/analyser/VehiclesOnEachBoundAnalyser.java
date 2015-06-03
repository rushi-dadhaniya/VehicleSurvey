package com.rushi.vehiclesurvey.analyser;

import java.util.List;
import java.util.Map;

import com.rushi.vehiclesurvey.vo.RoadBounds;
import com.rushi.vehiclesurvey.vo.VehicleVO;
import com.rushi.vehilcesurvey.util.PrintQueue;

public class VehiclesOnEachBoundAnalyser implements Analyse {

	public void doAnalysis(Map<Character, List<VehicleVO>> vehicleDataMap) {

		for(Map.Entry<Character,List<VehicleVO>> boundEntry : vehicleDataMap.entrySet()) {
			PrintQueue.getPrintQueue().add(boundEntry.getValue().size() +" vehicles is/are running on " + RoadBounds.valueOf(boundEntry.getKey().toString()).getBoundName());
		}
		
	}
	

}
