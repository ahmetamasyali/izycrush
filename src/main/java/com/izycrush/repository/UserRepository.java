package com.izycrush.repository;


import org.springframework.data.repository.CrudRepository;

import com.izycrush.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

	User findByUsername(String username);

	public User save(User user);

	
}
