package com.quakehelp.utility;

import java.util.Map;

public class DistrictFinder {

	public String getDistrictName(String locationName) {
		
		boolean flag = false;		
		
		for (Map.Entry<String, String> entry : DistrictVillageMapper.districtMap.entrySet()) {
			String key = entry.getKey();
			if(locationName.indexOf(key)>-1){
		    	System.out.println("District : " + key);
		    	flag = true;
		    	return key;
		    }
		}
		
		if(!flag) {
			for (Map.Entry<String, String> entry : DistrictVillageMapper.villageDistrictMap.entrySet()) {
				String key = entry.getKey();
			    String value = entry.getValue();
			    if(locationName.indexOf(key)>-1){
			    	System.out.println("Village : " + key + " District : " + value);
			    	return value;
			    }
			}
		}
		
		return "Unclassified";
	}
}
