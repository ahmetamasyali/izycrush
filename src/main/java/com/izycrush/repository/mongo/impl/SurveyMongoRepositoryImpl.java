package com.izycrush.repository.mongo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.izycrush.model.mongo.Survey;
import com.izycrush.repository.mongo.SurveyRepository;

@Repository
public class SurveyMongoRepositoryImpl implements SurveyRepository
{
	private final MongoTemplate mongoTemplate;

	@Autowired
	public SurveyMongoRepositoryImpl(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}


	@Override
	public String saveSurvey(Survey survey)
	{
		mongoTemplate.save(survey);
		return survey.id;
	}

	@Override
	public Survey getById(String id)
	{
		Query query = new Query(Criteria.where("id").is(id));
		return mongoTemplate.findOne(query, Survey.class);
	}

	@Override
	public Survey getByUserAndQuestion(String userId, Integer questionNo)
	{
		Query query = new Query(Criteria.where("userId").is(userId)
				.andOperator(Criteria.where("questionNo").is(questionNo)));

		return mongoTemplate.findOne(query, Survey.class);
	}

}
