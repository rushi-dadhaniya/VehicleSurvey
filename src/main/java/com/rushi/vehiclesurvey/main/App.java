package com.rushi.vehiclesurvey.main;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rushi.vehiclesurvey.analyser.AbstractAnalyser;
import com.rushi.vehiclesurvey.analyser.VehicleAnalyserFactory;
import com.rushi.vehiclesurvey.builder.VehicleDataBuilder;
import com.rushi.vehiclesurvey.criteria.QueryCriteria;
import com.rushi.vehiclesurvey.reader.FileStreamReader;
import com.rushi.vehiclesurvey.reader.QueryReader;
import com.rushi.vehiclesurvey.util.PrintQueue;
import com.rushi.vehiclesurvey.util.PrintUtil;
import com.rushi.vehiclesurvey.util.VehicleDataFactory;
import com.rushi.vehiclesurvey.vo.DirectionAttributes;
import com.rushi.vehiclesurvey.vo.Messages;
import com.rushi.vehiclesurvey.vo.RoadBounds;
import com.rushi.vehiclesurvey.vo.VehicleVO;

public class App 
{
    public static void main( String[] args ) throws IOException
    {
        if(args.length >= 1) {
        	String path = args[0];
        	List<String> vehicleReadings = readData(path);
        	Map<Character, List<VehicleVO>> vehicleDataMap = buildData(vehicleReadings);
        	List<QueryCriteria> queryCriterias = readQuery();
        	analyseData(queryCriterias, vehicleDataMap);
       }
        else {
        	PrintQueue.getPrintQueue().add(Messages.FILE_PATH_NOT_PROVIDED.getMessage());
        }
        printData();
    }

	private static List<QueryCriteria> readQuery() throws NumberFormatException, IOException {
		QueryReader queryReader = new QueryReader();
		return queryReader.read();
	}

	private static Map<Character, List<VehicleVO>> buildData(List<String> vehicleReadings) {
		VehicleDataBuilder vehicleDataBuilder = new VehicleDataBuilder();
    	vehicleDataBuilder.build(vehicleReadings);
    	return VehicleDataFactory.getInstance();
	}

	private static void analyseData(List<QueryCriteria> queryCriterias, Map<Character, List<VehicleVO>> vehicleDataMap) {

		for(QueryCriteria queryCriteria : queryCriterias) {
			List<AbstractAnalyser> vehicleAnalysers = VehicleAnalyserFactory.getInstance(queryCriteria);
			Map<Character, List<VehicleVO>> vehicleDirectionMap = getDataBasedOnDirection(queryCriteria, vehicleDataMap);
			for(AbstractAnalyser analyser: vehicleAnalysers) {
				PrintQueue.getPrintQueue().add(queryCriteria.toString());
				analyser.doAnalysis(queryCriteria, vehicleDirectionMap);
			}
		}
		
		
	}

	private static Map<Character, List<VehicleVO>> getDataBasedOnDirection(QueryCriteria queryCriteria,
			Map<Character, List<VehicleVO>> vehicleDataMap) {

		Character direction = null;
		if(queryCriteria.getDirection().equals(DirectionAttributes.NORTH.getDirectionAttribute())) {
			direction = RoadBounds.valueOf("NORTH").getBound();
		}
		else if(queryCriteria.getDirection().equals(DirectionAttributes.SOUTH.getDirectionAttribute())) {
			direction = RoadBounds.valueOf("SOUTH").getBound();
		}
		if(direction != null) {
			List<VehicleVO> vehicles = vehicleDataMap.get(direction);
			Map<Character, List<VehicleVO>> vehicleDirectionMap = new HashMap<Character, List<VehicleVO>>();
			vehicleDirectionMap.put(direction, vehicles);
			return vehicleDirectionMap;
		}
		return vehicleDataMap;
		
	}

	private static void printData() {
		List<String> printQueue = PrintQueue.getPrintQueue();
		for(String message : printQueue) {
			PrintUtil.print(message);
		}
	}

	private static List<String> readData(String path) {
		FileStreamReader fileStreamReader = new  FileStreamReader();
		return fileStreamReader.read(path);
	}
}
