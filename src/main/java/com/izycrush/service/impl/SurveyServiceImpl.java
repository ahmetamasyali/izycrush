package com.izycrush.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.izycrush.model.mongo.Question;
import com.izycrush.model.mongo.Survey;
import com.izycrush.model.mongo.User;
import com.izycrush.repository.mongo.SurveyRepository;
import com.izycrush.service.QuestionService;
import com.izycrush.service.SurveyService;
import com.izycrush.service.UserService;

@Service
public class SurveyServiceImpl implements SurveyService
{


	private static final Logger logger = Logger.getLogger(SurveyServiceImpl.class);

	private SurveyRepository surveyRepository;

	@Autowired
	private QuestionService questionService;

	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;

	public SurveyServiceImpl(SurveyRepository surveyRepository) {
		this.surveyRepository = surveyRepository;

	}

	@Override
	public void saveAnswers(User user, List<Survey> surveys)
	{
		for(Survey survey : surveys){
			Survey existedSurvey = getByUserAndQuestion(user.id, survey.getQuestionNo());

			if(existedSurvey != null)
			{
				existedSurvey.setResponse(survey.getResponse());
				saveSurvey(existedSurvey);
			}
			else
			{
				survey.setUserId(user.id);
				saveSurvey(survey);
			}
		}
		userService.save(user);
	}

	@Override
	public String saveSurvey(Survey survey)
	{
		return surveyRepository.saveSurvey(survey);
	}

	@Override
	public Survey getById(String id)
	{
		return surveyRepository.getById(id);
	}

	@Override
	public Survey getByUserAndQuestion(String userId, Integer questionNo)
	{
		return surveyRepository.getByUserAndQuestion(userId, questionNo);
	}

	@Override
	public double calculateMatchPoint(User user1, User user2)
	{
		List<Question> questions = questionService.loadQuestions();

		double matchedQuestion = 0;
		questions.forEach(question ->{

		});
		for(Question question : questions)
		{
			Survey survey1 = getByUserAndQuestion(user1.id, question.getQuestionNo());
			Survey survey2 = getByUserAndQuestion(user2.id, question.getQuestionNo());

			if(survey1 != null && survey2 != null && survey1.getResponse() == survey2.getResponse())
			{
				matchedQuestion ++;
			}
		}

		return matchedQuestion / new Double(questions.size());
	}


}
