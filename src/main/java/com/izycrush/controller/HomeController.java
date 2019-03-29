package com.izycrush.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.izycrush.model.mongo.User;
import com.izycrush.rest.BaseResponse;
import com.izycrush.rest.IzycrushException;

@Controller
public class HomeController  extends BaseController {
	
	private static final Logger logger = Logger.getLogger(HomeController.class);
	
	@RequestMapping("/")
	String home(Model model) {
		logger.debug("going to home page");
		if(isLoggedIn()) {
			model.addAttribute("isLoggedIn", true);
			model.addAttribute("username",getUsername());
		}else {
			model.addAttribute("isLoggedIn", false);
		}
		return "home";
	}

	@RequestMapping(value = "/loadAllUsers", method = RequestMethod.GET)
	@ResponseBody
	public BaseResponse<List<User>> loadAllUsers() throws IzycrushException
	{
		if(!isLoggedIn())
		{
			throw new IzycrushException("User must be logged in");
		}
		return BaseResponse.success(userService.loadAllUsers());
	}
	
}
