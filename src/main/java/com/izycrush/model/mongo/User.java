package com.izycrush.model.mongo;

import java.util.Date;
import java.util.Set;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.izycrush.enums.Role;

@Document(collection = "user")
public class User{

	@Id
	public String id;

	private String name;

	@Indexed(unique = true)
	private String username;

	@JsonIgnore
	private String passwordHash;

	@JsonIgnore
    private Set<Role> roles;

	private Date lastActivityDate;

	@Transient
	private boolean isOnline;
	 
	@Transient
	private String password;
	
	@Transient
	private String passwordConfirm;

	@Transient
	private Double matchPoint;

	public User() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Date getLastActivityDate()
	{
		return lastActivityDate;
	}

	public void setLastActivityDate(Date lastActivityDate)
	{
		this.lastActivityDate = lastActivityDate;
	}

	public boolean isOnline()
	{
		return isOnline;
	}

	public void setOnline(boolean online)
	{
		isOnline = online;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public Double getMatchPoint()
	{
		return matchPoint;
	}

	public void setMatchPoint(Double matchPoint)
	{
		this.matchPoint = matchPoint;
	}
}