package com.izycrush.model.mongo;


import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Document(collection = "question")
public class Question
{

	@Id
	@JsonIgnore
	public String id;

	@Indexed(unique = true)
	private Integer questionNo;

	private String questionText;

	@Transient
	private Boolean response = null;

	public Question(Integer questionNo, String questionText)
	{
		this.questionNo = questionNo;
		this.questionText = questionText;
	}
	public Integer getQuestionNo()
	{
		return questionNo;
	}

	public void setQuestionNo(Integer questionNo)
	{
		this.questionNo = questionNo;
	}

	public String getQuestionText()
	{
		return questionText;
	}

	public void setQuestionText(String questionText)
	{
		this.questionText = questionText;
	}

	public Boolean getResponse()
	{
		return response;
	}

	public void setResponse(Boolean response)
	{
		this.response = response;
	}
}