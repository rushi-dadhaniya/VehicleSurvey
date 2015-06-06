package com.rushi.vehiclesurvey.util;

import junit.framework.Assert;

import org.junit.Test;

public class SpeedUtilTest {

	@Test
	public void calculateSpeedWithValidTime() {
		double speed = SpeedUtil.calculateSpeed(0.000025);
		Assert.assertEquals(100.0, speed);
	}
	
	@Test
	public void calculateSpeedWithTime0() {
		double speed = SpeedUtil.calculateSpeed(0.0);
		Assert.assertEquals(0.0, speed);
	}

}
