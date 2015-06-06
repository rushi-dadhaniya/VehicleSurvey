package com.rushi.vehiclesurvey.parser;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class VehicleDataParserTest {

	private static VehicleDataParser vehicleDataParser;
	
	@BeforeClass
	public static void setUp() {
		vehicleDataParser = new VehicleDataParser();
	}
	
	@Test(expected = StringIndexOutOfBoundsException.class)
	public void getRoadBoundWithEmptyLine() {
		vehicleDataParser.getRoadBound("");
	}
	
	@Test(expected = NumberFormatException.class)
	public void getTimeInMilliswithInvalidLine() {
		vehicleDataParser.getTimeInMillis("A");
	}
	
	@Test(expected = NumberFormatException.class)
	public void getTimeInMilliswithInValidTime() {
		vehicleDataParser.getTimeInMillis("AAAA");
	}
	
	@Test
	public void getRoadBoundWithBound() {
		Assert.assertEquals(new Character('A'), vehicleDataParser.getRoadBound("A12345"));
	}
	
	@Test
	public void getTimeInMilliswithValidTime() {
		Assert.assertEquals(new Long(12345), vehicleDataParser.getTimeInMillis("A12345"));
	}
	
	@Test
	public void getRoadBoundWithNull() {
		Assert.assertNull(vehicleDataParser.getRoadBound(null));
	}
	
	@Test
	public void getTimeInMillisWithNull() {
		Assert.assertNull(vehicleDataParser.getTimeInMillis(null));
	}

}
