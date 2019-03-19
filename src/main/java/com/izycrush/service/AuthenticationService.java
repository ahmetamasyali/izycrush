package com.izycrush.service;

public interface AuthenticationService {

	boolean login(String username, String password);

	String loggedInUsername();

}
