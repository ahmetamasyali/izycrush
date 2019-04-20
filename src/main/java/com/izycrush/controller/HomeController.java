package com.izycrush.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.izycrush.model.mongo.Survey;
import com.izycrush.model.mongo.User;
import com.izycrush.rest.BaseResponse;
import com.izycrush.rest.IzycrushException;
import com.izycrush.service.SurveyService;
import com.izycrush.service.UserService;

@Controller
public class HomeController  extends BaseController {
	
	private static final Logger logger = Logger.getLogger(HomeController.class);

	@Autowired
	private SurveyService surveyService;

	@RequestMapping("/")
	String home(Model model) {
		logger.debug("going to home page");
		if(isLoggedIn()) {
			User user = getUser();
			model.addAttribute("isLoggedIn", true);
			model.addAttribute("username", user.getUsername());
			model.addAttribute("profileImage", user.getProfileImage());

			Survey survey = surveyService.getByUserAndQuestion(user.id, 1);
			model.addAttribute("surveyFilled", survey != null);
		}
		else
		{
			model.addAttribute("isLoggedIn", false);
			model.addAttribute("surveyFilled", false);
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

		User user = getUser();
		return BaseResponse.success(userService.loadAllUsers(user));
	}
	
}
