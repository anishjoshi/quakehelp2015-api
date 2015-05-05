package com.quakehelp;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.quakehelp.api.domain.QuakeData;
import com.quakehelp.client.QuakeHelpClient;
import com.quakehelp.utility.DistrictFinder;
import com.quakehelp.utility.DistrictVillageMapper;

@Component
public class ApplicationStartup {

	Logger logger = LoggerFactory.getLogger(ApplicationStartup.class);

	public static QuakeData quakeData;
	
	@Autowired
	private DistrictVillageMapper districtVillageMapper;
	@Autowired
	private DistrictFinder districtFinder;

	@PostConstruct
	public void init() {
		districtVillageMapper.mapDistrictVillage();
		this.getStartUpQuakeData();

	}

	public void getStartUpQuakeData() {
		try {
			quakeData = QuakeHelpClient.getQuakeInfo();
			if (quakeData != null) {
				for (int i = 0; i < quakeData.getPayload().getIncidents()
						.size(); i++) {
					quakeData
							.getPayload()
							.getIncidents()
							.get(i)
							.getIncident()
							.setDistrict(
									districtFinder.getDistrictName(quakeData
											.getPayload().getIncidents().get(i)
											.getIncident().getLocationname()
											.toLowerCase(), quakeData
											.getPayload().getIncidents().get(i)
											.getIncident()
											.getLocationlatitude(), quakeData
											.getPayload().getIncidents().get(i)
											.getIncident()
											.getLocationlongitude()));
				}

				logger.info("Message::::" + quakeData.getError().getMessage());
			}
		} catch (IllegalArgumentException ex) {
			logger.info("Error Message" + ex.getMessage());
			quakeData = new QuakeData();
		}

	}

}
