package com.quakehelp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.quakehelp.ApplicationStartup;
import com.quakehelp.api.domain.QuakeData;

@RestController
@RequestMapping(value = "/quakeinfo")
public class QuakeInformationController {

	@RequestMapping(method = RequestMethod.GET)
	public QuakeData getQuakeData() {
		return ApplicationStartup.quakeData;
	}

}
