package com.rushi.vehiclesurvey.main;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.rushi.vehiclesurvey.analyser.Analyser;
import com.rushi.vehiclesurvey.analyser.VehicleAnalyserFactory;
import com.rushi.vehiclesurvey.builder.VehicleDataBuilder;
import com.rushi.vehiclesurvey.criteria.QueryCriteria;
import com.rushi.vehiclesurvey.reader.FileStreamReader;
import com.rushi.vehiclesurvey.reader.QueryReader;
import com.rushi.vehiclesurvey.vo.Messages;
import com.rushi.vehiclesurvey.vo.VehicleVO;
import com.rushi.vehilcesurvey.util.PrintQueue;
import com.rushi.vehilcesurvey.util.PrintUtil;
import com.rushi.vehilcesurvey.util.VehicleDataFactory;

public class App 
{
    public static void main( String[] args ) throws IOException
    {
        if(args.length >= 1) {
        	String path = args[0];
        	List<String> vehicleReadings = readData(path);
        	Map<Character, List<VehicleVO>> vehicleDataMap = buildData(vehicleReadings);
        	List<QueryCriteria> queryCriterias = readQuery();
        	System.out.println(queryCriterias);
//        	analyseData(vehicleDataMap);
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

	private static void analyseData(
			Map<Character, List<VehicleVO>> vehicleDataMap) {

		List<Analyser> vehicleAnalysers = VehicleAnalyserFactory.getInstance();
		for(Analyser analyser: vehicleAnalysers) {
			analyser.doAnalysis(vehicleDataMap);
		}
		
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
