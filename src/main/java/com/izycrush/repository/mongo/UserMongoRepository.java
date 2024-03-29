package com.izycrush.repository.mongo;

import java.util.List;

import com.izycrush.model.mongo.User;

public interface UserMongoRepository
{

	User findByUsername(String username);

	User findById(String id);

	User save(User user);

	List<User> loadAllUsers();

}
