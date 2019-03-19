package com.izycrush.controller;

import org.apache.log4j.Logger;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.HtmlUtils;

import com.izycrush.model.Greeting;

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


	@MessageMapping("/hello")
	@SendTo("/topic/greetings")
	public Greeting greeting(Greeting message) throws Exception {
		Thread.sleep(1000); // simulated delay
		return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getContent()) + "!");
	}



	
	
}
