package com.izycrush.model.mongo;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "conversation")
public class Conversation
{

	@Id
	public String id;

	private List<String> userIds;

	private Date createdDate;

	private Date lastMessageDate;

	@Transient
	private User speakingPerson;

	@Transient
	private Message lastMessage;

	@Transient
	private List<Message> messages;

	public Conversation() {}

	public List<String> getUserIds()
	{
		return userIds;
	}

	public void setUserIds(List<String> userIds)
	{
		this.userIds = userIds;
	}

	public Date getCreatedDate()
	{
		return createdDate;
	}

	public void setCreatedDate(Date createdDate)
	{
		this.createdDate = createdDate;
	}

	public Date getLastMessageDate()
	{
		return lastMessageDate;
	}

	public void setLastMessageDate(Date lastMessageDate)
	{
		this.lastMessageDate = lastMessageDate;
	}

	public User getSpeakingPerson()
	{
		return speakingPerson;
	}

	public void setSpeakingPerson(User speakingPerson)
	{
		this.speakingPerson = speakingPerson;
	}

	public Message getLastMessage()
	{
		return lastMessage;
	}

	public void setLastMessage(Message lastMessage)
	{
		this.lastMessage = lastMessage;
	}

	public List<Message> getMessages()
	{
		return messages;
	}

	public void setMessages(List<Message> messages)
	{
		this.messages = messages;
	}
}