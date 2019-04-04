package com.izycrush.service;

import java.util.List;

import com.izycrush.model.mongo.User;

public interface UserService{

	User findByUsername(String username);

	User findById(String id);

	User save(User user);

	List<User> loadAllUsers(User loggedInUser);
}
