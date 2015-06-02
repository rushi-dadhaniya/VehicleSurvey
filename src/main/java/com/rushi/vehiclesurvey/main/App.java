package com.rushi.vehiclesurvey.main;

import java.util.List;
import java.util.Map;

import com.rushi.vehiclesurvey.builder.VehicleDataBuilder;
import com.rushi.vehiclesurvey.reader.FileStreamReader;
import com.rushi.vehiclesurvey.vo.Date;
import com.rushi.vehiclesurvey.vo.Messages;
import com.rushi.vehiclesurvey.vo.RoadBounds;
import com.rushi.vehilcesurvey.util.PrintQueue;
import com.rushi.vehilcesurvey.util.PrintUtil;
import com.rushi.vehilcesurvey.util.VehicleData;

public class App 
{
    public static void main( String[] args )
    {
        if(args.length >= 1) {
        	String path = args[0];
        	List<String> lines = readData(path);
        	
        	System.out.println("Input:");
        	
        	for(String line : lines) {
        		System.out.println(line);
        	}
        	
        	VehicleDataBuilder vehicleDataBuilder = new VehicleDataBuilder();
        	
        	vehicleDataBuilder.build(lines);
        	
        	Map<Character, List<Date>> instance = VehicleData.getInstance();
        	
        	System.out.println(instance);
        	
        	System.out.println("map");
        	
        	for(Map.Entry<Character, List<Date>> list : instance.entrySet()) {
        		System.out.println("****");
        		for(Date date : list.getValue()) {
        			System.out.println(date);
        		}
        	}
        	
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
