package com.izycrush.service;

import java.util.List;

import com.izycrush.model.mongo.Conversation;
import com.izycrush.model.mongo.Message;
import com.izycrush.model.mongo.User;
import com.izycrush.rest.IzycrushException;

public interface ConversationService
{
	Message addNewMessage(String conversationId, String username, String messageText) throws IzycrushException;

	String createConversation(User loggedInUser, String targetUsername) throws IzycrushException;

	long updateConversation(Conversation conversation);

	List<Conversation> getByUserId(String userId);

	Conversation getById(String conversationId);
}
