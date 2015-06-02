package com.rushi.vehiclesurvey.main;

import java.util.List;
import java.util.Map;

import com.rushi.vehiclesurvey.builder.VehicleDataBuilder;
import com.rushi.vehiclesurvey.reader.FileStreamReader;
import com.rushi.vehiclesurvey.vo.Messages;
import com.rushi.vehiclesurvey.vo.VehicleVO;
import com.rushi.vehilcesurvey.util.PrintQueue;
import com.rushi.vehilcesurvey.util.PrintUtil;
import com.rushi.vehilcesurvey.util.VehicleDataFactory;

public class App 
{
    public static void main( String[] args )
    {
        if(args.length >= 1) {
        	String path = args[0];
        	List<String> vehicleReadings = readData(path);
        	
        	System.out.println("Input:");
        	
        	for(String vehicleReading : vehicleReadings) {
        		System.out.println(vehicleReading);
        	}
        	
        	VehicleDataBuilder vehicleDataBuilder = new VehicleDataBuilder();
        	
        	vehicleDataBuilder.build(vehicleReadings);
        	
        	Map<Character, List<VehicleVO>> vehicleDataList = VehicleDataFactory.getInstance();
        	
        	System.out.println(vehicleDataList);
        	
        }
        else {
        	PrintQueue.getPrintQueue().add(Messages.FILE_PATH_NOT_PROVIDED.getMessage());
        }
        printData();
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
