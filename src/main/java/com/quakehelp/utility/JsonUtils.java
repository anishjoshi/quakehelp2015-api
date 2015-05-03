package com.quakehelp.utility;

import com.google.gson.Gson;

public class JsonUtils {

	public static <T> T toObject(String data, Class<T> type) {
		Gson gson = new Gson();
		return gson.fromJson(data, type);
	}

	public static String toString(Object src) {
		Gson gson = new Gson();
		return gson.toJson(src);
	}

}