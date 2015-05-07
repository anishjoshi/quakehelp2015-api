package com.quakehelp.client;

import com.github.kevinsawicki.http.HttpRequest;
import com.google.gson.JsonSyntaxException;
import com.quakehelp.api.domain.QuakeCategoryData;
import com.quakehelp.api.domain.QuakeData;
import com.quakehelp.utility.JsonUtils;

public class QuakeHelpClient {

	public static QuakeData getQuakeInfo() {
		QuakeData quakeData = new QuakeData();
		try {
			HttpRequest httpRequest = HttpRequest.get(AppConfig.QUAKE_INFO)
					.contentType(AppConfig.CONTENT_TYPE_JSON)
					.accept(AppConfig.ACCEPT_TYPE).trustAllCerts()
					.trustAllHosts();

			if (httpRequest.ok()) {
				quakeData = JsonUtils.toObject(httpRequest.body(),
						QuakeData.class);

			}
		} catch (HttpRequest.HttpRequestException | JsonSyntaxException exception) {
			throw new IllegalArgumentException(exception.getMessage());
		}
		return quakeData;
	}

	public static QuakeCategoryData getQuakeCategoryInfo() {
		QuakeCategoryData quakeCategoryData = new QuakeCategoryData();
		try {
			HttpRequest httpRequest = HttpRequest.get(AppConfig.CATEGORY_INFO)
					.contentType(AppConfig.CONTENT_TYPE_JSON)
					.accept(AppConfig.ACCEPT_TYPE).trustAllCerts()
					.trustAllHosts();

			if (httpRequest.ok()) {
				quakeCategoryData = JsonUtils.toObject(httpRequest.body(),
						QuakeCategoryData.class);

			}
		} catch (HttpRequest.HttpRequestException | JsonSyntaxException exception) {
			throw new IllegalArgumentException(exception.getMessage());
		}
		return quakeCategoryData;
	}

}
