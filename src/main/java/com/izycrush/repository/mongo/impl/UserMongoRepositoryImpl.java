package com.izycrush.repository.mongo.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.izycrush.model.mongo.User;
import com.izycrush.repository.mongo.UserMongoRepository;

@Repository
public class UserMongoRepositoryImpl implements UserMongoRepository
{
	private final MongoTemplate mongoTemplate;

	@Autowired
	public UserMongoRepositoryImpl(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	@Override
	public User findByUsername(String username)
	{
		Query query = new Query(Criteria.where("username").is(username));

		return mongoTemplate.findOne(query, User.class);
	}

	@Override
	public User findById(String id)
	{
		Query query = new Query(Criteria.where("id").is(id));

		return mongoTemplate.findOne(query, User.class);
	}

	@Override
	public User save(User user)
	{
		mongoTemplate.save(user);

		return user;
	}

	@Override
	public List<User> loadAllUsers()
	{
		return mongoTemplate.findAll(User.class);
	}

}
