package com.rushi.vehiclesurvey.util;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.rushi.vehiclesurvey.util.TimeUtil;
import com.rushi.vehiclesurvey.vo.Date;

public class TimeUtilTest {

	private TimeUtil timeUtil;
	private Date expectedDate;
	
	@Before
	public void setUp() {
		timeUtil = new TimeUtil();
		expectedDate = new Date();
	}
	
	@Test
	public void inNewDayWithDifferentDayData() {
		Assert.assertTrue(timeUtil.isNewDay(new Long(2000), new Long(1000)));
	}
	
	@Test
	public void inNewDayWithSameDayData() {
		Assert.assertFalse(timeUtil.isNewDay(new Long(1000), new Long(2000)));
	}
	
	@Test
	public void inNewDayWithNull() {
		Assert.assertFalse(timeUtil.isNewDay(null, null));
	}
	
	@Test
	public void millsToHoursWithValidValue() {
		Double hours = timeUtil.convertMilliSecondsToHours(new Long(3600000));
		Assert.assertEquals(new Double(1), hours);
	}
	
	@Test
	public void millsToHoursWithNull() {
		Assert.assertNull(timeUtil.convertMilliSecondsToHours(null));
	}
	
	@Test
	public void convertMilliSecondsToDateNewEntryNextDay() {
		expectedDate.setDay(3);
		expectedDate.setHours(1);
		expectedDate.setMinutes(10);
		expectedDate.setSeconds(30);
		Date actualDate = timeUtil.convertMilliSecondsToDate(new Long(6230000), new Long(4230000), 2);
		Assert.assertTrue(expectedDate.equals(actualDate));
	}
	
	@Test
	public void convertMilliSecondsToDateNewEntrySameDay() {
		expectedDate.setDay(1);
		expectedDate.setHours(0);
		expectedDate.setMinutes(0);
		expectedDate.setSeconds(6);
		Date actualDate = timeUtil.convertMilliSecondsToDate(new Long(4000), new Long(6000), 1);
		Assert.assertTrue(expectedDate.equals(actualDate));
	}
	
	@Test
	public void convertMilliSecondsToDateNewEntry() {
		expectedDate.setDay(1);
		expectedDate.setHours(0);
		expectedDate.setMinutes(0);
		expectedDate.setSeconds(6);
		Date actualDate = timeUtil.convertMilliSecondsToDate(new Long(0), new Long(6000), 1);
		Assert.assertTrue(expectedDate.equals(actualDate));
	}
	
	@Test
	public void convertMilliSecondsToDateWithNull() {
		Date actualDate = timeUtil.convertMilliSecondsToDate(null, null, 1);
		Assert.assertNull(actualDate);
	}

}
