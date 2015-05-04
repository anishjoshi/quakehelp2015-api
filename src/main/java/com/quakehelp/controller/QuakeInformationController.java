package com.quakehelp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quakehelp.ApplicationStartup;
import com.quakehelp.api.domain.CategoryData;
import com.quakehelp.api.domain.Incident;
import com.quakehelp.api.domain.QuakeData;
import com.quakehelp.api.domain.QuakeInformation;
import com.quakehelp.utility.PaginatedArrayList;

@RestController
@RequestMapping(value = "/quakeinfo")
public class QuakeInformationController {

	@RequestMapping(method = RequestMethod.GET)
	public Map<String, List<QuakeInformation>> getQuakeData() {
		Map<String, List<QuakeInformation>> quakeMap = new HashMap<>();
		List<QuakeInformation> quakeInfos = new ArrayList<>();
		quakeInfos = ApplicationStartup.quakeData.getPayload().getIncidents();
		quakeMap.put("incidents", quakeInfos);
		return quakeMap;
	}

	@RequestMapping(value = "page", method = RequestMethod.GET)
	public Map<String, List<QuakeInformation>> getPaginatedQuakeData() {
		Map<String, List<QuakeInformation>> quakeMap = new HashMap<>();

		List<QuakeInformation> quakeInfos = new ArrayList<>();
		PaginatedArrayList pg = new PaginatedArrayList(
				ApplicationStartup.quakeData.getPayload().getIncidents(), 10);
		quakeInfos = pg.subList(1, 10);
		quakeMap.put("incidents", quakeInfos);
		return quakeMap;
	}

	/***
	 * clients.stream().forEach(
	 * 
	 * client -> client.getFeatures().stream() .filter(feature ->
	 * feature.getId().equals(featureId)) .forEach(feature ->
	 * feature.setFlag(flag))); clients.forEach(client -> this.update(client));
	 */

	@RequestMapping(method = RequestMethod.GET, params = "categoryId")
	public Map<String, List<QuakeInformation>> getQuakeDataByCategory(
			@RequestParam("categoryId") int categoryId) {

		Map<String, List<QuakeInformation>> quakeMap = new HashMap<>();

		List<QuakeInformation> quakeInfos = new ArrayList<>();
		for (QuakeInformation qi : ApplicationStartup.quakeData.getPayload()
				.getIncidents()) {
			for (CategoryData category : qi.getCategories()) {

				if (category.getCategory().getId() == categoryId) {
					quakeInfos.add(qi);
				}
			}
		}
		quakeMap.put("incidents", quakeInfos);
		return quakeMap;
	}

	@RequestMapping(method = RequestMethod.GET, params = "incidentId")
	public Map<String, List<QuakeInformation>> getQuakeDataByIncident(
			@RequestParam("incidentId") String incidentId) {
		Map<String, List<QuakeInformation>> quakeMap = new HashMap<>();
		List<QuakeInformation> quakeInfos = new ArrayList<>();
		for (QuakeInformation qi : ApplicationStartup.quakeData.getPayload()
				.getIncidents()) {
			if (qi.getIncident().getIncidentid().equals(incidentId)) {
				quakeInfos.add(qi);
			}
		}
		quakeMap.put("incidents", quakeInfos);
		return quakeMap;
	}

}
