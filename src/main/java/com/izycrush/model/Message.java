package com.izycrush.model;

public class Message
{
    private User sender;
    private String value;

    public Message() {
    }

    public Message(String value, String username) {
        this.value = value;
        this.sender = new User();
        this.sender.setUsername(username);
    }


    public User getSender()
    {
        return sender;
    }

    public void setSender(User sender)
    {
        this.sender = sender;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }
}
