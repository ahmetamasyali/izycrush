package com.izycrush.repository.mongo.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.izycrush.model.mongo.Conversation;
import com.izycrush.repository.mongo.ConversationRepository;
import com.mongodb.client.result.UpdateResult;

@Repository
public class ConversationMongoRepositoryImpl implements ConversationRepository
{
	private final MongoTemplate mongoTemplate;

	@Autowired
	public ConversationMongoRepositoryImpl(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	@Override
	public String saveConversation(Conversation conversation) {
		mongoTemplate.save(conversation);

		return conversation.id;
	}

	@Override
	public long updateConversation(Conversation conversation) {

		Query query = new Query(Criteria.where("id").is(conversation.id));
		Update update = new Update();
		update.set("lastMessageDate", conversation.getLastMessageDate());

		UpdateResult result = mongoTemplate.updateFirst(query, update, Conversation.class);

		if(result!=null)
			return result.getModifiedCount();
		else
			return 0;

	}

	@Override
	public List<Conversation> getByUserId(String userId)
	{
		Query query = new Query(Criteria.where("userIds").in(userId));

		return mongoTemplate.find(query, Conversation.class);
	}

	@Override
	public Conversation getByTwoUserId(String userId1, String userId2)
	{
		Query query = new Query(Criteria.where("userIds").in(userId1)
				.andOperator(Criteria.where("userIds").in(userId2),
						Criteria.where("userIds").size(2)));

		return mongoTemplate.findOne(query, Conversation.class);
	}

	@Override
	public Conversation getById(String conversationId)
	{
		Query query = new Query(Criteria.where("id").is(conversationId));

		return mongoTemplate.findOne(query, Conversation.class);
	}
}
