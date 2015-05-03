package com.quakehelp;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.quakehelp.api.domain.QuakeData;
import com.quakehelp.client.QuakeHelpClient;
import com.quakehelp.utility.DistrictVillageMapper;

@Component
public class ApplicationStartup {

	public static QuakeData quakeData;

	@PostConstruct
	public void init() {
		this.getStartUpQuakeData();
		new DistrictVillageMapper();
	}

	public void getStartUpQuakeData() {
		quakeData = QuakeHelpClient.getQuakeInfo();
		System.out.println("Message::::" + quakeData.getError().getMessage());
	}

}
