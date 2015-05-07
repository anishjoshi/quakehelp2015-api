package com.quakehelp.api.domain;

import java.util.ArrayList;
import java.util.List;

public class Payload {

	private String domain;
	private List<QuakeInformation> incidents;

	public Payload() {
		this.incidents = new ArrayList<>();
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public List<QuakeInformation> getIncidents() {
		return incidents;
	}

	public void setIncidents(List<QuakeInformation> incidents) {
		this.incidents = incidents;
	}

}
