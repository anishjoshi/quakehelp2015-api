package com.quakehelp.utility;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

public class DistrictFinder {

	public static String getDistrictName(String locationName, String latitude, String longitude) {
		String response = DistrictFinder.getDristictNameFromMap(locationName);
		if(response.equals("Unclassified")){
			try {
				String jsonValue = CoordinateDecoder.sendGet(latitude, longitude);
				
				JSONParser parser=new JSONParser();
				Object obj = parser.parse(jsonValue);
		        JSONArray array = (JSONArray)obj;
		        HashMap<String,Object> obj1 = (HashMap<String, Object>) array.get(0);
		        String displayName = obj1.get("display_name").toString();
		        
		        response = DistrictFinder.getDristictNameFromMap(displayName);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return response;
	}
	
	public static String getDristictNameFromMap(String locationName){
		boolean flag = false;

		for (Map.Entry<String, String> entry : DistrictVillageMapper.districtMap
				.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			if (locationName.toLowerCase().indexOf(key) > -1) {
				flag = true;
				return value;
			}
		}

		if (!flag) {
			for (Map.Entry<String, String> entry : DistrictVillageMapper.villageDistrictMap
					.entrySet()) {
				String key = entry.getKey();
				String value = entry.getValue();
				if (locationName.toLowerCase().indexOf(key) > -1) {
					return value;
				}
			}
		}
		return "Unclassified";
	}
}
