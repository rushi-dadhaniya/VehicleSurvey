package com.rushi.vehilcesurvey.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rushi.vehiclesurvey.vo.Date;
import com.rushi.vehiclesurvey.vo.RoadBounds;

public class VehicleDataFactory {

	private static Map<Character, List<Date>> vehicleDataListMap = new HashMap<Character, List<Date>>();

	public static Map<Character, List<Date>> getInstance() {

		if (vehicleDataListMap.isEmpty()) {
			for (RoadBounds roadBound : RoadBounds.values()) {
				vehicleDataListMap.put(roadBound.getBound(),
						new ArrayList<Date>());
			}
		}
		return vehicleDataListMap;
	}

}
