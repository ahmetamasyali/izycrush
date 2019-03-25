package com.izycrush.controller;

import org.apache.log4j.Logger;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.HtmlUtils;

import com.izycrush.model.Message;

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


	@MessageMapping("/hello/{conversationId}")
	@SendTo("/topic/greetings/{conversationId}")
	public Message greeting(@DestinationVariable String conversationId, SimpMessageHeaderAccessor headerAccessor, String text) throws Exception {
		Thread.sleep(200); // simulated delay
		String username = headerAccessor.getUser().getName();

		if(StringUtils.isEmpty(username))
		{
			throw new Exception("User must be logged in");
		}
		Message message = new Message(HtmlUtils.htmlEscape(text), username);
		return message;
	}



	
	
}
