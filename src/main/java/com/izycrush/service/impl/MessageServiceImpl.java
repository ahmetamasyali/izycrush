package com.izycrush.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izycrush.model.mongo.Conversation;
import com.izycrush.model.mongo.Message;
import com.izycrush.model.mongo.User;
import com.izycrush.repository.mongo.MessageRepository;
import com.izycrush.rest.ErrorCodes;
import com.izycrush.rest.IzycrushException;
import com.izycrush.service.ConversationService;
import com.izycrush.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService
{

	@Autowired
	private ConversationService conversationService;

	private static final Logger logger = Logger.getLogger(MessageServiceImpl.class);

	private MessageRepository messageRepository;

	public MessageServiceImpl(MessageRepository messageRepository) {
		this.messageRepository = messageRepository;
		
	}

	@Override
	public String saveMessage(Message message)
	{
		return messageRepository.saveMessage(message);
	}

	@Override
	public List<Message> loadConversationMessages(User loggedInUser, String conversationId) throws IzycrushException
	{
		Conversation conversation = conversationService.getById(conversationId);

		if(!conversation.getUserIds().contains(loggedInUser.id))
		{
			logger.error("Conversation not found with id : " + conversationId);
			throw new IzycrushException(ErrorCodes.CONVERSATION_NOT_FOUND);
		}

		return messageRepository.getByConversationId(conversationId);
	}

	@Override
	public Message getLastByUserId(String userId)
	{
		return messageRepository.getLastByUserId(userId);
	}

	@Override
	public Message getLastByConversationId(String conversationId)
	{
		return messageRepository.getLastByUserId(conversationId);
	}
}
