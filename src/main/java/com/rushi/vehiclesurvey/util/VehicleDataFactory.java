package com.rushi.vehiclesurvey.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rushi.vehiclesurvey.vo.RoadBounds;
import com.rushi.vehiclesurvey.vo.VehicleVO;

public class VehicleDataFactory {

	private static Map<Character, List<VehicleVO>> vehicleDataListMap = new HashMap<Character, List<VehicleVO>>();

	public static Map<Character, List<VehicleVO>> getInstance() {

		if (vehicleDataListMap.isEmpty()) {
			for (RoadBounds roadBound : RoadBounds.values()) {
				vehicleDataListMap.put(roadBound.getBound(),
						new ArrayList<VehicleVO>());
			}
		}
		return vehicleDataListMap;
	}

}
