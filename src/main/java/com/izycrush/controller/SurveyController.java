package com.izycrush.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.izycrush.model.mongo.Question;
import com.izycrush.model.mongo.Survey;
import com.izycrush.model.mongo.User;
import com.izycrush.rest.BaseResponse;
import com.izycrush.rest.IzycrushException;
import com.izycrush.service.QuestionService;
import com.izycrush.service.SurveyService;

@Controller
public class SurveyController extends BaseController {
	
	private static final Logger logger = Logger.getLogger(SurveyController.class);

	@Autowired
	private SurveyService surveyService;

	@Autowired
	private QuestionService questionService;

	@RequestMapping(value = "/loadQuestions", method = RequestMethod.GET)
	@ResponseBody
	public BaseResponse<List<Question>> loadQuestions()
	{
		User user = getUser();
		return BaseResponse.success(questionService.loadQuestions());
	}

	@RequestMapping(value = "/loadSurvey", method = RequestMethod.GET)
	@ResponseBody
	public BaseResponse<List<Question>> loadConversations()
	{
		User user = getUser();

		List<Question> questions = questionService.loadQuestions();

		for(Question question :questions)
		{
			Survey survey = surveyService.getByUserAndQuestion(user.id, question.getQuestionNo());

			if(survey != null)
			{
				question.setResponse(survey.getResponse());
			}
		}
		return BaseResponse.success(questions);
	}

	@RequestMapping(value = "/saveSurvey", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse<Boolean> loadConversation(@RequestBody List<Survey> surveys) throws IzycrushException
	{
		User user = getUser();

		surveyService.saveAnswers(user, surveys);

		return BaseResponse.success(true);
	}

}
