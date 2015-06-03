package com.rushi.vehiclesurvey.analyser;

import java.util.ArrayList;
import java.util.List;

import com.rushi.vehiclesurvey.criteria.QueryCriteria;
import com.rushi.vehiclesurvey.vo.AnalysisAttributes;

public class VehicleAnalyserFactory {

	private static List<Analyser> vehicleAnalysers = new ArrayList<Analyser>();
	
	public static List<Analyser> getInstance(QueryCriteria queryCriteria){
		
		vehicleAnalysers.removeAll(vehicleAnalysers);
		
		if(queryCriteria.getAnalysisAttribute().equals(AnalysisAttributes.VEHICLES.getAnalysisAttribute())) {
			vehicleAnalysers.add(new VehiclesAnalyser());
		}
		else if(queryCriteria.getAnalysisAttribute().equals(AnalysisAttributes.SPEED_DISTRIBUTION.getAnalysisAttribute())) {
			vehicleAnalysers.add(new SpeedAnalyser());
		}
		else {
			vehicleAnalysers.add(new DistanceAnalyser());
		}

		return vehicleAnalysers;
	}
	
}
