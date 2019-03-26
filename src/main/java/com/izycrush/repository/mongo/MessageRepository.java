package com.izycrush.repository.mongo;


import java.util.List;

import com.izycrush.model.mongo.Message;

public interface MessageRepository
{

	String saveMessage(Message message);

	List<Message> getByConversationId(String conversationId);

	Message getLastByUserId(String userId);

}
