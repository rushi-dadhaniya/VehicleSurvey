package com.rushi.vehiclesurvey.analyser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rushi.vehiclesurvey.criteria.QueryCriteria;
import com.rushi.vehiclesurvey.util.NumberUtil;
import com.rushi.vehiclesurvey.util.PrintQueue;
import com.rushi.vehiclesurvey.vo.VehicleVO;

public class DistanceAnalyser extends AbstractAnalyser {

	private static final String DISTANCE_UNTI = "km";

	@Override
	public void totalVehiclesAnalysis(QueryCriteria queryCriteria, Map<Character, List<VehicleVO>> vehicleDataMap) {
		perHourVehicleAnalysis(queryCriteria, vehicleDataMap);
	}

	@Override
	void perDayVehicleAnalysis(QueryCriteria queryCriteria, Map<Character, List<VehicleVO>> vehicleDataMap) {
		
		Map<Integer, Double> perHourVehicleMap = new HashMap<Integer, Double>();
		
		for(Map.Entry<Character, List<VehicleVO>> entry : vehicleDataMap.entrySet()) {
			List<VehicleVO> vehicles = entry.getValue();
			double distance = 0;
			int vehiclesCount = 0;
			int day = 0;
			for(int vehicleIterator = 0 ; vehicleIterator < vehicles.size() - 1 ; vehicleIterator++) {
				
				VehicleVO vehicle1 = vehicles.get(vehicleIterator);
				VehicleVO vehicle2 = vehicles.get(vehicleIterator + 1);
				if(vehicle1.getStartDate().getDay() == vehicle2.getStartDate().getDay()) {
					vehiclesCount += 1;
					distance += calculateDistance(vehicles.get(vehicleIterator), vehicles.get(vehicleIterator + 1));
					day = vehicle1.getStartDate().getDay();
				}
				else {
					if(distance > 0) {
						distance = NumberUtil.format(distance / (vehiclesCount + (vehiclesCount - 1)));
						perHourVehicleMap.put(day, distance);
						
					}
					else {
						distance = 0;
						vehiclesCount = 0;
						day = 0;
					}
				}
			}
		}
		
		print(perHourVehicleMap);
	}

	@Override
	void perHourVehicleAnalysis(QueryCriteria queryCriteria, Map<Character, List<VehicleVO>> vehicleDataMap) {
		
		Map<Integer, Double> perHourVehicleMap = new HashMap<Integer, Double>();
		
		for(Map.Entry<Character, List<VehicleVO>> entry : vehicleDataMap.entrySet()) {
			List<VehicleVO> vehicles = entry.getValue();
			double distance = 0;
			int vehiclesCount = 0;
			int hour = 0;
			for(int vehicleIterator = 0 ; vehicleIterator < vehicles.size() - 1 ; vehicleIterator++) {
				
				VehicleVO vehicle1 = vehicles.get(vehicleIterator);
				VehicleVO vehicle2 = vehicles.get(vehicleIterator + 1);
				if(vehicle1.getStartDate().getHours() == vehicle2.getStartDate().getHours()) {
					vehiclesCount += 1;
					distance += calculateDistance(vehicles.get(vehicleIterator), vehicles.get(vehicleIterator + 1));
					hour = vehicle1.getStartDate().getHours();
				}
				else {
					if(distance > 0) {
						distance = NumberUtil.format(distance / (vehiclesCount + (vehiclesCount - 1)));
						perHourVehicleMap.put(hour, distance);
						
					}
					else {
						distance = 0;
						vehiclesCount = 0;
						hour = 0;
					}
				}
			}
		}
		
		print(perHourVehicleMap);
		
	}

	private void print(Map<Integer, Double> map) {

		for(Map.Entry<Integer, Double> entry : map.entrySet()) {
			PrintQueue.getPrintQueue().add(entry.getKey() +":" + entry.getValue() + " " + DISTANCE_UNTI);
		}
		
	}

	@Override
	void morningVSEvening(QueryCriteria queryCriteria,
			Map<Character, List<VehicleVO>> vehicleDataMap) {
		
		List<Double> morningVehicleTraffic = new ArrayList<Double>();
		List<Double> eveningVehicleTraffic = new ArrayList<Double>();
		
		for(Map.Entry<Character, List<VehicleVO>> entry : vehicleDataMap.entrySet()) {
			List<VehicleVO> vehicles = entry.getValue();
			double distance = 0;
			for(int vehicleIterator = 0 ; vehicleIterator < vehicles.size() - 1 ; vehicleIterator++) {
				
				VehicleVO vehicle1 = vehicles.get(vehicleIterator);
				VehicleVO vehicle2 = vehicles.get(vehicleIterator + 1);
				if(isVehicleInMorningSlot(vehicle1.getStartDate(), vehicle1.getEndDate()) && isVehicleInMorningSlot(vehicle2.getStartDate(), vehicle2.getEndDate())) {
					distance = calculateDistance(vehicles.get(vehicleIterator), vehicles.get(vehicleIterator + 1));
					morningVehicleTraffic.add(distance);
				}
				else if(!isVehicleInMorningSlot(vehicle1.getStartDate(), vehicle1.getEndDate()) && isVehicleInMorningSlot(vehicle2.getStartDate(), vehicle2.getEndDate())) {
					distance = calculateDistance(vehicles.get(vehicleIterator), vehicles.get(vehicleIterator + 1));
					eveningVehicleTraffic.add(distance);
				}
			}
		}
		double totalDistance = 0;
		for(Double distance : morningVehicleTraffic) {
			totalDistance += distance;
		}
		double distanceDistribution = NumberUtil.format(totalDistance / (morningVehicleTraffic.size() + morningVehicleTraffic.size() - 1));
		PrintQueue.getPrintQueue().add("Morning traffic distance distribution: " + distanceDistribution + " " + DISTANCE_UNTI);
		totalDistance = 0;
		for(Double distance : eveningVehicleTraffic) {
			totalDistance += distance;
		}
		distanceDistribution = NumberUtil.format(totalDistance / (eveningVehicleTraffic.size() + eveningVehicleTraffic.size() - 1));
		PrintQueue.getPrintQueue().add("Evening traffic distance distribution: " + distanceDistribution + " " + DISTANCE_UNTI);
	}

}

