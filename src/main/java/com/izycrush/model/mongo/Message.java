package com.izycrush.model.mongo;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "message")
public class Message
{

	@Id
	public String id;

	private String senderUserId;

	private String conversationId;

	private Date sentDate;

	private String value;

	public Message() {}

	public String getSenderUserId()
	{
		return senderUserId;
	}

	public void setSenderUserId(String senderUserId)
	{
		this.senderUserId = senderUserId;
	}

	public String getConversationId()
	{
		return conversationId;
	}

	public void setConversationId(String conversationId)
	{
		this.conversationId = conversationId;
	}

	public Date getSentDate()
	{
		return sentDate;
	}

	public void setSentDate(Date sentDate)
	{
		this.sentDate = sentDate;
	}

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}
}