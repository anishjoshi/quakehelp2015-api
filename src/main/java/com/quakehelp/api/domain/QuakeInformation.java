package com.quakehelp.api.domain;

import java.util.List;

public class QuakeInformation {

	private Incident incident;
	private List<CategoryData> categories;
	private Object customfields;

	public Incident getIncident() {
		return incident;
	}

	public void setIncident(Incident incident) {
		this.incident = incident;
	}

	public List<CategoryData> getCategories() {
		return categories;
	}

	public void setCategories(List<CategoryData> categoryDatas) {
		this.categories = categoryDatas;
	}

	public Object getCustomfields() {
		return customfields;
	}

	public void setCustomfields(Object customfields) {
		this.customfields = customfields;
	}

}
