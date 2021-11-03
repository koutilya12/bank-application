package com.icin.bankapplication.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.icin.bankapplication.constants.BankApplicationConstants;
import com.icin.bankapplication.constants.helpers.Validator;
import com.icin.bankapplication.dao.UserDao;
import com.icin.bankapplication.entity.Response;
import com.icin.bankapplication.entity.User;
import com.icin.bankapplication.entity.UserSearchCriteria;
import com.icin.bankapplication.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserDao userDao;
	
	@Override
	public Response registerUser(User user) {
		String errorMessage = Validator.validateUser(user);
		if(errorMessage != null) {
			return new Response(BankApplicationConstants.FAILED, errorMessage);
		}
		userDao.save(user);
		if (user.getUserId() == null) {
			return new Response(BankApplicationConstants.FAILED, "Registration Failed");
		} 
		return new Response(BankApplicationConstants.SUCCESS, "User registered successfully");
	}

	@Override
	public Response userLogin(String emailId, String password, String mobileNum) {
		String errorMessage = Validator.validateUserLogin(emailId, password, mobileNum);
		if(errorMessage != null) {
			return new Response(BankApplicationConstants.FAILED, errorMessage);
		}
		if(userDao.validateLogin(emailId, password, mobileNum) != null) {
			return new Response(BankApplicationConstants.SUCCESS, "Login successful");
		}else {
			return new Response(BankApplicationConstants.FAILED, "Invalid credentials");
		}
	}

	@Transactional
	@Override
	public Response changePassword(Long userId, String oldPassword, String newPassword) {
		String errorMessage = Validator.validateChangePassword(userId, oldPassword, newPassword);
		if(errorMessage != null) {
			return new Response(BankApplicationConstants.FAILED, errorMessage);
		}
		if(userDao.validateChangePassword(userId, oldPassword) == null) {
			return new Response(BankApplicationConstants.FAILED, "Invalid credentials");
		}
		if(userDao.changePassword(userId, newPassword) == 0) {
			return new Response(BankApplicationConstants.FAILED, "Password change failed");
		}
		return new Response(BankApplicationConstants.SUCCESS, "Password changed successfully");
	}

	@Transactional
	@Override
	public Response updateContactDetails(User user) {
		String errorMessage = Validator.validateContactDetails(user);
		if(errorMessage != null) {
			return new Response(BankApplicationConstants.FAILED, errorMessage);
		}
		if(userDao.updateContactDetails(user.getUserId(), user.getAddress(), user.getEmailId(), checkFlagValue(user.getAddress()), checkFlagValue(user.getEmailId())) == 0) {
			return new Response(BankApplicationConstants.FAILED, "update contact details failed");
		}
		return new Response(BankApplicationConstants.SUCCESS, "Contact details updated successfully");
	}

	private int checkFlagValue(String value) {
		if(value != null) {
			return 1;
		}
		return 0;
	}

	@Override
	public Response getUserDetails(User user) {
		String errorMessage = Validator.validateUserSearchCriteria(user);
		if(errorMessage != null) {
			return new Response(BankApplicationConstants.FAILED, errorMessage);
		}	
		Example  example = Example.of(user);
		List<User> userDetails = userDao.findAll(example);
		return new Response(BankApplicationConstants.SUCCESS, userDetails);
	}
}
