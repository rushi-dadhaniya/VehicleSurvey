package com.rushi.vehiclesurvey.builder;

import com.rushi.vehiclesurvey.criteria.QueryCriteria;
import com.rushi.vehiclesurvey.vo.AnalysisAttributes;
import com.rushi.vehiclesurvey.vo.DirectionAttributes;
import com.rushi.vehiclesurvey.vo.TimeFrame;

public class QueryCriteriaBuilder {

	public QueryCriteria build(int analysisAttribute, int timeFrame, int directionAttribute) {
		
		QueryCriteria queryCriteria = new QueryCriteria();
		setAnalysisAttribute(analysisAttribute, queryCriteria);
		setTimeFrame(timeFrame, queryCriteria);
		setDirectionAttribute(directionAttribute, queryCriteria);
		return queryCriteria;
	}

	private void setAnalysisAttribute(int analysisAttribute, QueryCriteria queryCriteria) {
		for(AnalysisAttributes analysisAttributes : AnalysisAttributes.values()) {
		
			if(analysisAttributes.getChoice() == analysisAttribute) {
				queryCriteria.setAnalysisAttribute(analysisAttributes.getAnalysisAttribute());
			}
			
		}
	}
	
	private void setTimeFrame(int timeFrame, QueryCriteria queryCriteria) {
		for(TimeFrame timeFrames : TimeFrame.values()) {
		
			if(timeFrames.getChoice() == timeFrame) {
				queryCriteria.setTimeFrame(timeFrames.getTimeFrame());
			}
			
		}
	}
	
	private void setDirectionAttribute(int directionAttribute, QueryCriteria queryCriteria) {
		for(DirectionAttributes directionAttributes : DirectionAttributes.values()) {
		
			if(directionAttributes.getChoice() == directionAttribute) {
				queryCriteria.setDirection(directionAttributes.getDirectionAttribute());
			}
			
		}
	}
	
}
