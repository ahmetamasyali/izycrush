package com.izycrush.model.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Document(collection = "survey")
public class Survey
{

	@Id
	@JsonIgnore
	public String id;

	private Integer questionNo;

	private String userId;

	private boolean response;

	public Integer getQuestionNo()
	{
		return questionNo;
	}

	public void setQuestionNo(Integer questionNo)
	{
		this.questionNo = questionNo;
	}

	public String getUserId()
	{
		return userId;
	}

	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	public boolean getResponse()
	{
		return response;
	}

	public void setResponse(boolean response)
	{
		this.response = response;
	}
}