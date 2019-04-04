package com.izycrush.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import com.izycrush.model.mongo.Question;
import com.izycrush.repository.mongo.QuestionRepository;
import com.izycrush.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService
{


	private static final Logger logger = Logger.getLogger(QuestionServiceImpl.class);

	private QuestionRepository questionRepository;

	public QuestionServiceImpl(QuestionRepository questionRepository) {
		this.questionRepository = questionRepository;
		
	}

	@Override
	public String saveQuestion(Question question)
	{
		Question existedQuestion = getByNo(question.getQuestionNo());

		if(existedQuestion != null)
		{
			logger.debug("Question found with no : " + question.getQuestionNo());
			existedQuestion.setQuestionText(question.getQuestionText());
			return questionRepository.saveQuestion(existedQuestion);

		}
		return questionRepository.saveQuestion(question);
	}

	@Override
	public Question getByNo(Integer no)
	{
		return questionRepository.getByNo(no);
	}

	@Override
	public List<Question> loadQuestions()
	{
		return questionRepository.loadQuestions();
	}
}
