package com.rushi.vehiclesurvey.processor;

import java.util.List;

import com.rushi.vehiclesurvey.parser.VehicleDataParser;
import com.rushi.vehiclesurvey.validator.VehicleReadingValidator;
import com.rushi.vehiclesurvey.vo.Messages;
import com.rushi.vehilcesurvey.util.PrintQueue;

public class VehicleDataProcessor implements Processor {

	public void process(List<String> lines) {
		if(lines != null) {
			VehicleReadingValidator vehicleReadingValidator = new VehicleReadingValidator();
			for(String line : lines) {
				if(vehicleReadingValidator.isValid(line)) {
					VehicleDataParser vehicleDataParser = new VehicleDataParser();
					vehicleDataParser.parse(line);
				}
				else {
					PrintQueue.getPrintQueue().add(Messages.DATA_INVALID.getMessage() + line);
				}
			}
		}
		else {
			PrintQueue.getPrintQueue().add(Messages.FILE_EMPTY.getMessage());
		}
	}

}
