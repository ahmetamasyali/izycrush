package com.izycrush.service;

import java.util.List;

import com.izycrush.model.mongo.Message;
import com.izycrush.model.mongo.User;
import com.izycrush.rest.IzycrushException;

public interface MessageService
{
	String saveMessage(Message message) throws IzycrushException;

	List<Message> loadConversationMessages(User user, String conversationId) throws IzycrushException;

	Message getLastByUserId(String userId) throws IzycrushException;
}
