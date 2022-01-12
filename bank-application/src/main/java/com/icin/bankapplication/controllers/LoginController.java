package com.icin.bankapplication.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.icin.bankapplication.constants.BankApplicationConstants;
import com.icin.bankapplication.entity.Response;
import com.icin.bankapplication.entity.User;
import com.icin.bankapplication.filters.JwtUtils;
import com.icin.bankapplication.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/auth")
public class LoginController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserService userService;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/saveUser")
	public @ResponseBody Response saveUser(@RequestBody User user) {
		return userService.registerUser(user);

	}

	@PostMapping("/login")
	public @ResponseBody Response authenticateUser(@RequestBody User user) {
		Map<String,String> responsMap = new HashMap<>();		
		try {
			Authentication authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt = jwtUtils.generateJwtToken(authentication);
			responsMap.put("jwt",jwt);
			setUserObject(responsMap,user);
		} catch (Exception e) {
			log.error("Error occured : {}", e);
			return new Response(BankApplicationConstants.FAILED, e.getMessage());
		}
		return new Response(BankApplicationConstants.SUCCESS, null, responsMap);
	}

	private void setUserObject(Map<String, String> responsMap, User user) throws JsonProcessingException {
		User userSearchCriteria = new User();
		userSearchCriteria.setName(user.getName());
		Response response = userService.getUserDetails(userSearchCriteria);
		if(response.getStatus().equals(BankApplicationConstants.SUCCESS) && response.getData() != null) {
			@SuppressWarnings("unchecked")
			List<User> users = (List<User>) response.getData();
			if(users.isEmpty()) {
				return;
			}
			User userDetails = users.get(0);
			if(userDetails != null) {
		        ObjectMapper Obj = new ObjectMapper();
		        responsMap.put("user",Obj.writeValueAsString(userDetails));

			}
			
		}
		
	}

}
