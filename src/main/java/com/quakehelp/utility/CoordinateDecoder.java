/**
 * 
 */
package com.quakehelp.utility;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author prayas
 * Using openstreetmap api this class sends information according to what we need
 */
public class CoordinateDecoder {
	/**
	 * This function takes latitude and longtitude, then calls the openstreetmap api to get information and sends the json
	 * formatted string as a return value.
	 * @param latitude
	 * @param longitude
	 * @return
	 * @throws Exception
	 */
	public static String sendGet(String latitude, String longitude) throws Exception {
		 
		String url = "https://nominatim.openstreetmap.org/search?q="+latitude+"%2C"+longitude+"&format=json";
 
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
 
		// optional default is GET
		con.setRequestMethod("GET");
		con.setRequestProperty("Content-type", "application/json; charset=UTF-8");
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.8,hi;q=0.6,ne;q=0.4");
 
		//add request header
		con.setRequestProperty("User-Agent", "Mozilla/5.0");
 
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
 
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
 
		return response.toString();
 
	}

}
