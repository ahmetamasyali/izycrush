package com.izycrush.repository.mongo;

import com.izycrush.model.mongo.Survey;

public interface SurveyRepository
{

	String saveSurvey(Survey survey);

	Survey getById(String id);

	Survey getByUserAndQuestion(String userId, Integer questionNo);
}
