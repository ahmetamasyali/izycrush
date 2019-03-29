package com.izycrush.repository.mongo;


import java.util.List;

import com.izycrush.model.mongo.Conversation;

public interface ConversationRepository
{

	String saveConversation(Conversation conversation);

	long updateConversation(Conversation conversation);

	List<Conversation> getByUserId(String userId);

	Conversation getById(String conversationId);

	Conversation getByTwoUserId(String userId1, String userId2);
}
