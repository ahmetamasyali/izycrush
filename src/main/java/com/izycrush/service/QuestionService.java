package com.izycrush.service;

import java.util.List;

import com.izycrush.model.mongo.Question;

public interface QuestionService
{

	String saveQuestion(Question question);

	Question getByNo(Integer no);

	List<Question> loadQuestions();

}
