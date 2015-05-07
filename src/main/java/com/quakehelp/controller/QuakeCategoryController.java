package com.quakehelp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.quakehelp.api.domain.QuakeCategoryData;
import com.quakehelp.client.QuakeHelpClient;

@RestController
@RequestMapping(value = "/quakecategoryinfo")
public class QuakeCategoryController {

	@RequestMapping(method = RequestMethod.GET)
	public QuakeCategoryData getQuakeCategoryInfo() {
		return QuakeHelpClient.getQuakeCategoryInfo();
	}

}
