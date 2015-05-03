package com.quakehelp.utility;

import java.util.Map;

public class DistrictFinder {

	public static String getDistrictName(String locationName) {

		boolean flag = false;

		for (Map.Entry<String, String> entry : DistrictVillageMapper.districtMap
				.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			if (locationName.indexOf(key) > -1) {
				flag = true;
				return value;
			}
		}

		if (!flag) {
			for (Map.Entry<String, String> entry : DistrictVillageMapper.villageDistrictMap
					.entrySet()) {
				String key = entry.getKey();
				String value = entry.getValue();
				if (locationName.indexOf(key) > -1) {
					return value;
				}
			}
		}

		return "Unclassified";
	}
}
