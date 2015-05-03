package com.quakehelp.api.domain;

import java.util.List;

public class QuakeInformation {

	private Incident incident;
	private List<Category> categories;
	private Object customfields;

	public Incident getIncident() {
		return incident;
	}

	public void setIncident(Incident incident) {
		this.incident = incident;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public Object getCustomfields() {
		return customfields;
	}

	public void setCustomfields(Object customfields) {
		this.customfields = customfields;
	}

}
