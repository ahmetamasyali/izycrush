package com.izycrush.repository.mongo;

import java.util.List;

import com.izycrush.model.mongo.Question;

public interface QuestionRepository
{

	String saveQuestion(Question question);

	Question getByNo(Integer no);

	List<Question> loadQuestions();

}
