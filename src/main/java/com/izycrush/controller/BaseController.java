package com.izycrush.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.izycrush.service.AuthenticationService;


public abstract class BaseController {

	private static final Logger logger = Logger.getLogger(BaseController.class);

	@Autowired
	public AuthenticationService authenticationService;


	public boolean isLoggedIn() {
		logger.debug("isLoggedIn called");
		return !StringUtils.isEmpty(authenticationService.loggedInUsername());
	}
	
	public String getUsername() {
		logger.debug("getUsername called");
		return authenticationService.loggedInUsername();
	}


}
