package com.icin.bankapplication.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icin.bankapplication.entity.Response;
import com.icin.bankapplication.entity.User;
import com.icin.bankapplication.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	
	public UserController() {
		
	}
	
	@PostMapping("/saveUser")
	public @ResponseBody Response saveUser(@RequestBody User user) {
		return userService.registerUser(user);
	}
	
	@GetMapping("/getUsers")
	public @ResponseBody Response getUsers(@RequestBody User user) {
		return userService.getUserDetails(user);
	}
		
	@PostMapping("/changePassword") 
	public @ResponseBody  Response changePassword(@RequestBody Map<String, String> map) {
		Long userId = null;
		if(map.get("userId") != null) {
		userId = Long.parseLong(map.get("userId"));
	    }
		String newPassword = map.get("newPassword");
		String oldPassword = map.get("oldPassword");
		
		return userService.changePassword(userId, oldPassword, newPassword);
	}
}