package com.izycrush.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.StringUtils;

import com.izycrush.model.mongo.User;
import com.izycrush.service.AuthenticationService;
import com.izycrush.service.UserService;


public abstract class BaseController {

	private static final Logger logger = Logger.getLogger(BaseController.class);

	@Autowired
	public AuthenticationService authenticationService;

	@Autowired
	@Qualifier("userServiceImpl")
	public UserService userService;


	public boolean isLoggedIn() {
		logger.debug("isLoggedIn called");
		return !StringUtils.isEmpty(authenticationService.loggedInUsername());
	}
	
	public String getUsername() {
		logger.debug("getUsername called");
		return authenticationService.loggedInUsername();
	}

	public User getUser() {
		String username = authenticationService.loggedInUsername();
		return userService.findByUsername(username);
	}


}
