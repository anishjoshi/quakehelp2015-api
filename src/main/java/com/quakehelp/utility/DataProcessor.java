package com.quakehelp.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.quakehelp.ApplicationStartup;

@Component
public class DataProcessor {

	@Autowired
	private ApplicationStartup applicationStartup;

	@Scheduled(cron = "0 0/50 * * * ?")
	public void syncQuakeData() {
		System.out.println("scheduler");
		applicationStartup.getStartUpQuakeData();
	}
}
