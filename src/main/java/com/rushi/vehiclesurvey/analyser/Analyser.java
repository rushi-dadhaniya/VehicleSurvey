package com.rushi.vehiclesurvey.analyser;

import java.util.List;
import java.util.Map;

import com.rushi.vehiclesurvey.criteria.QueryCriteria;
import com.rushi.vehiclesurvey.vo.VehicleVO;

public interface Analyser {

	public void doAnalysis(QueryCriteria queryCriteria, Map<Character, List<VehicleVO>> vehicleDataMap);
	
}
