package com.izycrush.service;

import com.izycrush.model.User;

public interface UserService{

	User findByUsername(String username);

	User save(User user);
}
