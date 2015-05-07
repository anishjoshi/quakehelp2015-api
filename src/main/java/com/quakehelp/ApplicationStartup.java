package com.quakehelp;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.quakehelp.api.domain.QuakeCategoryData;
import com.quakehelp.api.domain.QuakeData;
import com.quakehelp.client.QuakeHelpClient;
import com.quakehelp.utility.DistrictFinder;
import com.quakehelp.utility.DistrictVillageMapper;

@Component
public class ApplicationStartup {

	Logger logger = LoggerFactory.getLogger(ApplicationStartup.class);

	public static QuakeData quakeData;
	public static QuakeData backupQuakeData;
	public static QuakeCategoryData quakeCategoryData;
	public static QuakeCategoryData backupQuakeCategoryData;

	@Autowired
	private DistrictVillageMapper districtVillageMapper;
	@Autowired
	private DistrictFinder districtFinder;

	@PostConstruct
	public void init() {
		ApplicationStartup.quakeData = new QuakeData();
		ApplicationStartup.backupQuakeData = new QuakeData();
		ApplicationStartup.quakeCategoryData = new QuakeCategoryData();
		ApplicationStartup.backupQuakeCategoryData = new QuakeCategoryData();
		districtVillageMapper.mapDistrictVillage();
		this.getStartUpQuakeData();

	}

	public void getStartUpQuakeData() {
		try {
			this.getIncidentInfo();
			this.getCategoryInfo();
		} catch (IllegalArgumentException ex) {
			logger.info("Error Message" + ex.getMessage());
			quakeData = backupQuakeData;
			quakeCategoryData = backupQuakeCategoryData;
		}

	}

	public void getIncidentInfo() {
		quakeData = QuakeHelpClient.getQuakeInfo();
		if (quakeData.getError() != null) {
			// update back up quake data in case error occurs later
			ApplicationStartup.backupQuakeData = ApplicationStartup.quakeData;
			for (int i = 0; i < quakeData.getPayload().getIncidents().size(); i++) {
				quakeData
						.getPayload()
						.getIncidents()
						.get(i)
						.getIncident()
						.setDistrict(
								districtFinder.getDistrictName(quakeData
										.getPayload().getIncidents().get(i)
										.getIncident().getLocationname()
										.toLowerCase(), quakeData.getPayload()
										.getIncidents().get(i).getIncident()
										.getLocationlatitude(), quakeData
										.getPayload().getIncidents().get(i)
										.getIncident().getLocationlongitude()));
			}

			logger.info("Message::::" + quakeData.getError().getMessage());
		} else {
			quakeData = ApplicationStartup.backupQuakeData;
		}
	}

	public void getCategoryInfo() {
		quakeCategoryData = QuakeHelpClient.getQuakeCategoryInfo();
		if (quakeCategoryData.getError() != null) {
			backupQuakeCategoryData = quakeCategoryData;
		} else {
			quakeCategoryData = ApplicationStartup.backupQuakeCategoryData;
		}
	}

}
