package com.izycrush.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.izycrush.enums.Role;
import com.izycrush.model.mongo.User;
import com.izycrush.repository.mongo.UserMongoRepository;
import com.izycrush.service.UserService;

@Service
public class UserServiceImpl  implements UserDetailsService,UserService {

	private static final Logger logger = Logger.getLogger(UserServiceImpl.class);
	
	private UserMongoRepository userRepository;
	
	
	public UserServiceImpl(UserMongoRepository userRepository) {
		this.userRepository = userRepository;
		
	}

	public User save(User user) {
		logger.debug("saving user");
		return userRepository.save(user);
	}

	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		
		if(user == null) {
			logger.info("user not found");
			return null;
		}
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		for (Role role : user.getRoles()){
			grantedAuthorities.add(new SimpleGrantedAuthority(role.toString()));
		}
		logger.info("user created");
		
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPasswordHash(), grantedAuthorities);
	}

	@Override
	public List<User> loadAllUsers()
	{
		return userRepository.loadAllUsers();
	}

	@Override
	public User findByUsername(String username) {
		logger.debug("findByUsername called");
		return userRepository.findByUsername(username);
	}

	@Override
	public User findById(String id) {
		logger.debug("findByUsername called");
		return userRepository.findById(id);
	}
	

	

}
