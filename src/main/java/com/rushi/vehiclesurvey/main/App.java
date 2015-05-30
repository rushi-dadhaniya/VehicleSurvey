package com.rushi.vehiclesurvey.main;

import java.util.List;

import com.rushi.vehiclesurvey.reader.FileStreamReader;
import com.rushi.vehiclesurvey.vo.Messages;
import com.rushi.vehilcesurvey.util.PrintQueue;
import com.rushi.vehilcesurvey.util.PrintUtil;

public class App 
{
    public static void main( String[] args )
    {
        if(args.length >= 1) {
        	String path = args[0];
        	List<String> lines = readData(path);
        	System.out.println(lines);
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
