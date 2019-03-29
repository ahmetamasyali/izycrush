package com.izycrush.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.izycrush.model.mongo.Conversation;
import com.izycrush.model.mongo.Message;
import com.izycrush.model.mongo.User;
import com.izycrush.rest.BaseResponse;
import com.izycrush.rest.IzycrushException;
import com.izycrush.service.ConversationService;
import com.izycrush.service.MessageService;

@Controller
public class MessagingController extends BaseController {
	
	private static final Logger logger = Logger.getLogger(MessagingController.class);

	@Autowired
	private ConversationService conversationService;

	@Autowired
	private MessageService messageService;

	@RequestMapping(value = "/loadConversationMessages/{conversationId}", method = RequestMethod.GET)
	@ResponseBody
	public BaseResponse<List<Message>> loadConversationMessages(@PathVariable("conversationId")  String conversationId) throws IzycrushException
	{
		User user = getUser();
		return BaseResponse.success(messageService.loadConversationMessages(user, conversationId));
	}

	@RequestMapping(value = "/loadConversations", method = RequestMethod.GET)
	@ResponseBody
	public BaseResponse<List<Conversation>> loadConversations()
	{
		User user = getUser();
		return BaseResponse.success(conversationService.getByUserId(user.id));
	}

	@RequestMapping(value = "/loadConversation/{conversationId}", method = RequestMethod.GET)
	@ResponseBody
	public BaseResponse<Conversation> loadConversation(@PathVariable("conversationId") String conversationId) throws IzycrushException
	{
		User user = getUser();
		Conversation conversation = conversationService.loadConversationWithMessages(conversationId, user);

		return BaseResponse.success(conversation);
	}

	@RequestMapping(value = "/createNewConversation/{targetUsername}", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse<String> createNewConversation(@PathVariable("targetUsername")  String targetUsername) throws IzycrushException
	{
		User user = getUser();
		BaseResponse response = BaseResponse.success(conversationService.createConversation(user, targetUsername));
		return response;
	}

	@MessageMapping("/newMessage/{conversationId}")
	@SendTo("/topic/messaging/send/{conversationId}")
	public Message messagingSocket(@DestinationVariable String conversationId, SimpMessageHeaderAccessor headerAccessor, String text) throws Exception {
		Thread.sleep(50); // simulated delay
		String username = headerAccessor.getUser().getName();

		if(StringUtils.isEmpty(username))
		{
			throw new IzycrushException("User must be logged in");
		}
		logger.info("new message to conversation: " + conversationId);
		Message message = conversationService.addNewMessage(conversationId, username, text);
		message.setSender(userService.findById(message.getSenderUserId()));

		return message;
	}



	
	
}
