package com.rushi.vehiclesurvey.util;

import junit.framework.Assert;

import org.junit.Test;

public class NumberUtilTest {

	@Test
	public void formatWithValidNumber2() {
		double formatedDouble = NumberUtil.format(10.1212354564);
		Assert.assertEquals(10.12, formatedDouble);
	}
	
	@Test
	public void formatWithValidNumber() {
		double formatedDouble = NumberUtil.format(10.0);
		Assert.assertEquals(10.00, formatedDouble);
	}
	
	@Test
	public void maxWithValidNumbers() {
		int max = NumberUtil.max(10, 20);
		Assert.assertEquals(20, max);
	}

}
