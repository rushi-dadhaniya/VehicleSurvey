package com.rushi.vehiclesurvey.builder;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

import com.rushi.vehiclesurvey.criteria.QueryCriteria;
import com.rushi.vehiclesurvey.vo.AnalysisAttributes;
import com.rushi.vehiclesurvey.vo.DirectionAttributes;
import com.rushi.vehiclesurvey.vo.TimeFrame;

public class QueryCriteriaBuilderTest {

	private static QueryCriteriaBuilder queryCriteriaBuilder;
	@BeforeClass
	public static void setUp() {
		queryCriteriaBuilder = new QueryCriteriaBuilder();
	}
	
	@Test
	public void buildQueryCriteriaWithValidValues() {

		QueryCriteria queryCriteria = queryCriteriaBuilder.build(1, 2, 3);
		QueryCriteria expectedQueryCriteria = new QueryCriteria();
		expectedQueryCriteria.setAnalysisAttribute(AnalysisAttributes.VEHICLES.getAnalysisAttribute());
		expectedQueryCriteria.setTimeFrame(TimeFrame.PER_DAY.getTimeFrame());
		expectedQueryCriteria.setDirection(DirectionAttributes.ALL.getDirectionAttribute());
		
		Assert.assertTrue(expectedQueryCriteria.equals(queryCriteria));

	}
	
	@Test
	public void buildQueryCriteriaWithInvalidValues() {

		QueryCriteria queryCriteria = queryCriteriaBuilder.build(7, 8,9);
		QueryCriteria expectedQueryCriteria = new QueryCriteria();
		Assert.assertTrue(expectedQueryCriteria.equals(queryCriteria));

	}

}
