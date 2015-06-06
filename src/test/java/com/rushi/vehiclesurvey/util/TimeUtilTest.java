package com.rushi.vehiclesurvey.util;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.rushi.vehiclesurvey.util.TimeUtil;
import com.rushi.vehiclesurvey.vo.Date;

public class TimeUtilTest {

	private Date expectedDate;
	
	@Before
	public void setUp() {
		expectedDate = new Date();
	}
	
	@Test
	public void inNewDayWithDifferentDayData() {
		Assert.assertTrue(TimeUtil.isNewDay(new Long(2000), new Long(1000)));
	}
	
	@Test
	public void inNewDayWithSameDayData() {
		Assert.assertFalse(TimeUtil.isNewDay(new Long(1000), new Long(2000)));
	}
	
	@Test
	public void inNewDayWithNull() {
		Assert.assertFalse(TimeUtil.isNewDay(null, null));
	}
	
	@Test
	public void millsToHoursWithValidValue() {
		Double hours = TimeUtil.convertMilliSecondsToHours(new Long(3600000));
		Assert.assertEquals(new Double(1), hours);
	}
	
	@Test
	public void millsToHoursWithNull() {
		Assert.assertNull(TimeUtil.convertMilliSecondsToHours(null));
	}
	
	@Test
	public void convertMilliSecondsToDateNewEntryNextDay() {
		expectedDate.setDay(3);
		expectedDate.setHours(1);
		expectedDate.setMinutes(10);
		expectedDate.setSeconds(30);
		Date actualDate = TimeUtil.convertMilliSecondsToDate(new Long(6230000), new Long(4230000), 2);
		Assert.assertTrue(expectedDate.equals(actualDate));
	}
	
	@Test
	public void convertMilliSecondsToDateNewEntrySameDay() {
		expectedDate.setDay(1);
		expectedDate.setHours(0);
		expectedDate.setMinutes(0);
		expectedDate.setSeconds(6);
		Date actualDate = TimeUtil.convertMilliSecondsToDate(new Long(4000), new Long(6000), 1);
		Assert.assertTrue(expectedDate.equals(actualDate));
	}
	
	@Test
	public void convertMilliSecondsToDateNewEntry() {
		expectedDate.setDay(1);
		expectedDate.setHours(0);
		expectedDate.setMinutes(0);
		expectedDate.setSeconds(6);
		Date actualDate = TimeUtil.convertMilliSecondsToDate(new Long(0), new Long(6000), 1);
		Assert.assertTrue(expectedDate.equals(actualDate));
	}
	
	@Test
	public void convertMilliSecondsToDateWithNull() {
		Date actualDate = TimeUtil.convertMilliSecondsToDate(null, null, 1);
		Assert.assertNull(actualDate);
	}

}
