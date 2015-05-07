package com.quakehelp.api.domain;

public class QuakeCategoryData {

	private PayloadCategory payload;
	private Error error;

	public QuakeCategoryData() {
		super();
		this.payload = new PayloadCategory();
	}

	public PayloadCategory getPayload() {
		return payload;
	}

	public void setPayload(PayloadCategory payload) {
		this.payload = payload;
	}

	public Error getError() {
		return error;
	}

	public void setError(Error error) {
		this.error = error;
	}

}
