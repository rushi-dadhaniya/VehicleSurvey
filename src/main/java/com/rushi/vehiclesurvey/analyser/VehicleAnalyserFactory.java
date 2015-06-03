package com.rushi.vehiclesurvey.analyser;

import java.util.ArrayList;
import java.util.List;

public class VehicleAnalyserFactory {

	private static List<Analyser> vehicleAnalysers = new ArrayList<Analyser>();
	
	public static List<Analyser> getInstance(){
		if(vehicleAnalysers.isEmpty()) {
			vehicleAnalysers.add(new TotalVehiclaAnalyser());
			vehicleAnalysers.add(new VehiclesOnEachDirectionAnalyser());
			vehicleAnalysers.add(new MorningEveningTrafficAnalyser());
		}
		return vehicleAnalysers;
	}
	
}
