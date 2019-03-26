package com.izycrush.repository.mongo.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.izycrush.model.mongo.Message;
import com.izycrush.repository.mongo.MessageRepository;

@Repository
public class MessageMongoRepositoryImpl implements MessageRepository
{
	private final MongoTemplate mongoTemplate;

	@Autowired
	public MessageMongoRepositoryImpl(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	@Override
	public String saveMessage(Message message)
	{
		mongoTemplate.save(message);

		return message.id;
	}

	@Override
	public List<Message> getByConversationId(String conversationId)
	{
		Query query = new Query(Criteria.where(conversationId).is(conversationId));
		query.with(new Sort(Direction.ASC, "sentDate"));
		return mongoTemplate.find(query, Message.class);
	}

	@Override
	public Message getLastByUserId(String userId)
	{
		Query query = new Query(Criteria.where(userId).is(userId));
		query.with(new Sort(Direction.DESC, "conversationId"));
		return mongoTemplate.findOne(query, Message.class);
	}



}
