package com.rushi.vehiclesurvey.builder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.rushi.vehiclesurvey.util.VehicleDataFactory;
import com.rushi.vehiclesurvey.vo.Date;
import com.rushi.vehiclesurvey.vo.RoadBounds;
import com.rushi.vehiclesurvey.vo.VehicleVO;

public class VehicleBuilderTest {

	private static VehicleDataBuilder vehicleDataBuilder;
	private Map<Character, List<VehicleVO>> expectedVehicleDataMap;
	private List<String> vehicleReadings;
	
	@Before
	public void init() {
		expectedVehicleDataMap = new HashMap<Character, List<VehicleVO>>();
		for (RoadBounds roadBound : RoadBounds.values()) {
			expectedVehicleDataMap.put(roadBound.getBound(),
					new ArrayList<VehicleVO>());
		}
		vehicleReadings = new ArrayList<String>();
	}
	
	@BeforeClass
	public static void setUp() {
		vehicleDataBuilder = new VehicleDataBuilder();
	}
	
	@Test
	public void buildWithValidDataVehicleOnMultipleTrak() {
		vehicleReadings.add("A30000");
		vehicleReadings.add("B40000");
		vehicleReadings.add("A50000");
		vehicleReadings.add("B60000");
		
		vehicleDataBuilder.build(vehicleReadings);
		Map<Character, List<VehicleVO>> vehicleDataMap = VehicleDataFactory.getInstance();
		VehicleVO vehicle = new VehicleVO();
		vehicle.setBounds("B");
		Date startDate = getDate(1, 0, 0, 30);
		Date endDate = getDate(1, 0, 1, 0);
		vehicle.setStartDate(startDate);
		vehicle.setEndDate(endDate);
		vehicle.setStartMillis(new Long(30000));
		vehicle.setSpeed(0.45);
		List<VehicleVO> vehicles = expectedVehicleDataMap.get('B');
		vehicles.add(vehicle);
		Assert.assertEquals(expectedVehicleDataMap.get('B'), vehicleDataMap.get('B'));
		
	}
	
	@Test
	public void buildWithValidDataVehicleOnSingleTrak() {
		vehicleReadings.add("A10000");
		vehicleReadings.add("A20000");
		vehicleDataBuilder.build(vehicleReadings);
		Map<Character, List<VehicleVO>> vehicleDataMap = VehicleDataFactory.getInstance();
		VehicleVO vehicle = new VehicleVO();
		vehicle.setBounds("A");
		Date startDate = getDate(1, 0, 0, 10);
		Date endDate = getDate(1, 0, 0, 20);
		vehicle.setStartDate(startDate);
		vehicle.setEndDate(endDate);
		vehicle.setStartMillis(new Long(10000));
		vehicle.setSpeed(0.9);
		List<VehicleVO> vehicles = expectedVehicleDataMap.get('A');
		vehicles.add(vehicle);
		Assert.assertEquals(expectedVehicleDataMap, vehicleDataMap);
		
	}
	
	@Test
	public void buildWithNull() {
		vehicleDataBuilder.build(null);
		Map<Character, List<VehicleVO>> vehicleDataMap = VehicleDataFactory.getInstance();
		Assert.assertEquals(expectedVehicleDataMap, vehicleDataMap);
		
	}
	
	private Date getDate(int day, int hours, int minutes, int seconds) {
		Date date = new Date();
		date.setDay(day);
		date.setHours(hours);
		date.setMinutes(minutes);
		date.setSeconds(seconds);
		return date;
	}
	

}
