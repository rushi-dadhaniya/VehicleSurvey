package com.rushi.vehiclesurvey.analyser;

import java.util.ArrayList;
import java.util.List;

import com.rushi.vehiclesurvey.criteria.QueryCriteria;
import com.rushi.vehiclesurvey.vo.AnalysisAttributes;

public class VehicleAnalyserFactory {

	private static List<AbstractAnalyser> vehicleAnalysers = new ArrayList<AbstractAnalyser>();
	
	public static List<AbstractAnalyser> getInstance(QueryCriteria queryCriteria){
		
		vehicleAnalysers.removeAll(vehicleAnalysers);
		
		if(queryCriteria.getAnalysisAttribute().equals(AnalysisAttributes.VEHICLES.getAnalysisAttribute())) {
			vehicleAnalysers.add(new VehiclesAnalyser());
		}
		else if(queryCriteria.getAnalysisAttribute().equals(AnalysisAttributes.SPEED_DISTRIBUTION.getAnalysisAttribute())) {
			vehicleAnalysers.add(new SpeedAnalyser());
		}
		else if(queryCriteria.getAnalysisAttribute().equals(AnalysisAttributes.PICK_TIME.getAnalysisAttribute())) {
			vehicleAnalysers.add(new PickTimeAnalyser());
		}
		else {
			vehicleAnalysers.add(new DistanceAnalyser());
		}

		return vehicleAnalysers;
	}
	
}
