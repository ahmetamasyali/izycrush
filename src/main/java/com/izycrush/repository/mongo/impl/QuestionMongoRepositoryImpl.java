package com.izycrush.repository.mongo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.izycrush.model.mongo.Question;
import com.izycrush.repository.mongo.QuestionRepository;

@Repository
public class QuestionMongoRepositoryImpl implements QuestionRepository
{
	private final MongoTemplate mongoTemplate;

	@Autowired
	public QuestionMongoRepositoryImpl(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}


	@Override
	public String saveQuestion(Question question)
	{
		mongoTemplate.save(question);
		return question.id;
	}

	@Override
	public Question getByNo(Integer questionNo)
	{
		Query query = new Query(Criteria.where("questionNo").is(questionNo));
		return mongoTemplate.findOne(query, Question.class);
	}

	@Override
	public List<Question> loadQuestions()
	{
		Query query = new Query();
		query.with(new Sort(Direction.ASC, "questionNo"));
		return mongoTemplate.find(query, Question.class);
	}

}
