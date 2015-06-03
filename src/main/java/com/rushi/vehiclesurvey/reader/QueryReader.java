package com.rushi.vehiclesurvey.reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.rushi.vehiclesurvey.builder.QueryCriteriaBuilder;
import com.rushi.vehiclesurvey.criteria.QueryCriteria;
import com.rushi.vehiclesurvey.vo.AnalysisAttributes;
import com.rushi.vehiclesurvey.vo.DirectionAttributes;
import com.rushi.vehiclesurvey.vo.TimeFrame;

public class QueryReader {

	private List<QueryCriteria> queryCriterias;
	private QueryCriteriaBuilder queryCriteriaBuilder;
	
	public List<QueryCriteria> read() throws NumberFormatException, IOException {
		
		queryCriterias = new ArrayList<QueryCriteria>();
		queryCriteriaBuilder = new QueryCriteriaBuilder();
		
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    	String line = "";
    	do {
    		System.out.println("Select Attribute to analyse:");
    		int count = 1;
    		for(AnalysisAttributes analysisAttributes : AnalysisAttributes.values()) {
    			System.out.println(count++ + "." + analysisAttributes.getAnalysisAttribute());
    		}
    		int analysisAttribute = Integer.parseInt(bufferedReader.readLine());
    		System.out.println("Select analysis criteria:");
    		count = 1;
    		for(TimeFrame timeFrames : TimeFrame.values()) {
    			System.out.println(count++ + "." + timeFrames.getAnalysisAttribute());
    		}
    		int timeFrame = Integer.parseInt(bufferedReader.readLine());
    		System.out.println("Select direction:");
    		count = 1;
    		for(DirectionAttributes directionAttributes : DirectionAttributes.values()) {
    			System.out.println(count++ + "." + directionAttributes.getAnalysisAttribute());
    		}
    		int directionAttribute = Integer.parseInt(bufferedReader.readLine());
    		
    		QueryCriteria queryCriteria = queryCriteriaBuilder.build(analysisAttribute, timeFrame, directionAttribute);
    		queryCriterias.add(queryCriteria);
    		
    	}while(!(line = bufferedReader.readLine()).equals("done"));
    	
    	return queryCriterias;
	}
}
