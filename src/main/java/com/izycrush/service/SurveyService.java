package com.izycrush.service;

import java.util.List;

import com.izycrush.model.mongo.Survey;
import com.izycrush.model.mongo.User;

public interface SurveyService
{

	String saveSurvey(Survey survey);

	Survey getById(String id);

	Survey getByUserAndQuestion(String userId, Integer questionNo);

	void saveAnswers(User user, List<Survey> surveys);

	double calculateMatchPoint(User user1, User user2);
}
