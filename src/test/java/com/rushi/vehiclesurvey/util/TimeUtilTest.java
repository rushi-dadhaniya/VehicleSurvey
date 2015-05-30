package com.rushi.vehiclesurvey.util;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.rushi.vehiclesurvey.vo.Date;
import com.rushi.vehilcesurvey.util.TimeUtil;

public class TimeUtilTest {

	private TimeUtil timeUtil;
	private Date expectedDate;
	
	@Before
	public void setUp() {
		timeUtil = new TimeUtil();
		expectedDate = new Date();
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
