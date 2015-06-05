package com.rushi.vehiclesurvey.processor;

import java.util.List;

import com.rushi.vehiclesurvey.parser.VehicleDataParser;
import com.rushi.vehiclesurvey.util.PrintQueue;
import com.rushi.vehiclesurvey.validator.VehicleReadingValidator;
import com.rushi.vehiclesurvey.vo.Messages;

public class VehicleDataProcessor implements Processor {

	public void process(List<String> vehicleReadings) {
		if(vehicleReadings != null) {
			VehicleReadingValidator vehicleReadingValidator = new VehicleReadingValidator();
			for(String vehicleReading : vehicleReadings) {
				if(vehicleReadingValidator.isValid(vehicleReading)) {
					VehicleDataParser vehicleDataParser = new VehicleDataParser();
					vehicleDataParser.getTimeInMillis(vehicleReading);
				}
				else {
					PrintQueue.getPrintQueue().add(Messages.DATA_INVALID.getMessage() + vehicleReading);
				}
			}
		}
		else {
			PrintQueue.getPrintQueue().add(Messages.FILE_EMPTY.getMessage());
		}
	}

}
