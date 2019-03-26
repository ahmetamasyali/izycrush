package com.izycrush.model.mongo;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "conversation")
public class Conversation
{

	@Id
	public String id;

	private List<String> userIds;

	private Date createdDate;

	private Date lastMessageDate;

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
}