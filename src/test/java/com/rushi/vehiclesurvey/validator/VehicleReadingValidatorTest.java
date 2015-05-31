package com.rushi.vehiclesurvey.validator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class VehicleReadingValidatorTest {

	private VehicleReadingValidator vehicleReadingValidator;
	
	@Before
	public void setUp() {
		vehicleReadingValidator = new VehicleReadingValidator();
	}
	
	@Test
	public void isValidWithLowerCase() {
		Assert.assertFalse(vehicleReadingValidator.isValid("a1234"));
	}
	
	@Test
	public void isValidWithValidLineSingleDigit() {
		Assert.assertTrue(vehicleReadingValidator.isValid("A0"));
	}
	
	@Test
	public void isValidWithValidLineSecond() {
		Assert.assertTrue(vehicleReadingValidator.isValid("B4567"));
	}
	
	@Test
	public void isValidStartWithNumber() {
		Assert.assertFalse(vehicleReadingValidator.isValid("1234A1234"));
	}
	
	@Test
	public void isValidWithSpecialChars() {
		Assert.assertFalse(vehicleReadingValidator.isValid("B123.4"));
	}
	
	@Test
	public void isValidWithValidLine() {
		Assert.assertTrue(vehicleReadingValidator.isValid("A1234"));
	}
	
	@Test
	public void isValidWithoutTime() {
		Assert.assertFalse(vehicleReadingValidator.isValid("A"));
	}
	
	@Test
	public void isValidWithInvalidStart() {
		Assert.assertFalse(vehicleReadingValidator.isValid("C1234"));
	}
	
	@Test
	public void isValidWithNull() {
		Assert.assertFalse(vehicleReadingValidator.isValid(null));
	}

}
