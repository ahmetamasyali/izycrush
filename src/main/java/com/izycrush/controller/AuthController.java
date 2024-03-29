package com.izycrush.controller;

import java.util.HashSet;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.izycrush.enums.Role;
import com.izycrush.model.Login;
import com.izycrush.model.mongo.User;
import com.izycrush.rest.BaseResponse;
import com.izycrush.rest.ErrorCodes;
import com.izycrush.service.UserService;

@Controller
public class AuthController extends BaseController {

	private static final Logger logger = Logger.getLogger(AuthController.class);

	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@RequestMapping("/login")
	public String login(Model model) {
		if(isLoggedIn()) {
			return "redirect:/";
		}

		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public BaseResponse login(@RequestBody Login login) { 
		User user = userService.findByUsername(login.getUsername());

		if(user !=null) {
		
			try {
				if(authenticationService.login(user.getUsername(), login.getPassword())) {
					return BaseResponse.success("Login");

				}
			}catch(Exception e) {
				e.printStackTrace();
				logger.error("error while authentication",e);
			}
			return BaseResponse.error(ErrorCodes.WRONG_PASS);
		}


		return BaseResponse.error("Username Not Found");
	}

	@RequestMapping("/register")
	public String register(Model model) {
		if(isLoggedIn()) {
			return "redirect:/";
		}

		return "register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public BaseResponse register(@RequestBody User user) {
		if(user.getPassword() != null) {
			if(!user.getPassword().equals(user.getPasswordConfirm())) {
				return BaseResponse.error(ErrorCodes.PASSWORDS_NOT_EQUAL);
			}

		}else {
			return BaseResponse.error(ErrorCodes.PASSWORD_CANNOT_BE_NULL);
		}

		user.setPasswordHash(bCryptPasswordEncoder.encode(user.getPassword()));

		user.setRoles(new HashSet<Role>());
		user.getRoles().add(Role.USER);
		try {
			user = userService.save(user);
		}catch(DataIntegrityViolationException e) {
			logger.error("Username already Exist",e);
			e.printStackTrace();
			return BaseResponse.error(ErrorCodes.USERNAME_ALREADY_IN_USE);
		}catch(Exception e) {
			logger.error("error while registration",e);
			e.printStackTrace();
			return BaseResponse.error(ErrorCodes.ERROR);
		}

		return BaseResponse.success("Success");
	}



}
