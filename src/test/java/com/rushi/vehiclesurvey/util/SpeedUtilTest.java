package com.rushi.vehiclesurvey.util;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

public class SpeedUtilTest {

	private static SpeedUtil speedUtil;
	
	@BeforeClass
	public static void setUp() {
		speedUtil = new SpeedUtil();
	}
	
	@Test
	public void calculateSpeedWithValidTime() {
		double speed = speedUtil.calculateSpeed(0.000025);
		Assert.assertEquals(100.0, speed);
	}
	
	@Test
	public void calculateSpeedWithTime0() {
		double speed = speedUtil.calculateSpeed(0.0);
		Assert.assertEquals(0.0, speed);
	}

}
