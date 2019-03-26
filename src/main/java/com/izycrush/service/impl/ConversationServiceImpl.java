package com.izycrush.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izycrush.model.mongo.Conversation;
import com.izycrush.model.mongo.Message;
import com.izycrush.model.mongo.User;
import com.izycrush.repository.mongo.ConversationRepository;
import com.izycrush.rest.IzycrushException;
import com.izycrush.service.ConversationService;
import com.izycrush.service.MessageService;
import com.izycrush.service.UserService;

@Service
public class ConversationServiceImpl implements ConversationService
{

	@Autowired
	private UserService userService;

	@Autowired
	private MessageService messageService;

	private static final Logger logger = Logger.getLogger(ConversationServiceImpl.class);

	private ConversationRepository conversationRepository;

	public ConversationServiceImpl(ConversationRepository conversationRepository) {
		this.conversationRepository = conversationRepository;
		
	}

	@Override
	public Message addNewMessage(String conversationId, String username, String messageText) throws IzycrushException
	{
		User sender = userService.findByUsername(username);

		Conversation conversation = getById(conversationId);

		Date messageDate = new Date();

		Message message = new Message();
		message.setConversationId(conversationId);
		message.setSenderUserId(sender.id);
		message.setSentDate(messageDate);
		message.setValue(messageText);

		conversation.setLastMessageDate(messageDate);

		messageService.saveMessage(message);
		this.saveConversation(conversation);

		return message;
	}

	@Override
	public String createConversation(User loggedInUser, String targetUsername)
	{
		User targetUser = userService.findByUsername(targetUsername);

		Conversation conversation = new Conversation();
		conversation.setLastMessageDate(null);
		conversation.setCreatedDate(new Date());
		conversation.setUserIds(new ArrayList<>());

		conversation.getUserIds().add(loggedInUser.id);
		conversation.getUserIds().add(targetUser.id);

		this.saveConversation(conversation);

		logger.info("New conversation created with id : " + conversation.id);
		return conversation.id;
	}



	@Override
	public long updateConversation(Conversation conversation)
	{
		return conversationRepository.updateConversation(conversation);
	}

	@Override
	public List<Conversation> getByUserId(String userId)
	{
		return conversationRepository.getByUserId(userId);
	}

	@Override
	public Conversation getById(String conversationId)
	{
		return conversationRepository.getById(conversationId);
	}

	private String saveConversation(Conversation conversation)
	{
		return conversationRepository.saveConversation(conversation);
	}
}
