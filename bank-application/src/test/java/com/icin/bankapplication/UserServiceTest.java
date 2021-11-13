package com.icin.bankapplication;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.icin.bankapplication.constants.BankApplicationConstants;
import com.icin.bankapplication.constants.UserType;
import com.icin.bankapplication.dao.UserDao;
import com.icin.bankapplication.entity.Response;
import com.icin.bankapplication.entity.User;
import com.icin.bankapplication.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	UserService userService;
	
	public UserServiceTest() {
		
		
	}
	
	@Test
	public void registerUserTest() {
		User user = new User();
		user.setName("Smithss");
		user.setEmailId("smithisadmin@gmail.com");
		user.setPassword("smith@admin");
		user.setMobileNum("8888899990");
		user.setDateOfBirth(new Date());
		user.setAddress("Chicago");
		user.setUserType(UserType.ADMIN);
		
		Response response = userService.registerUser(user);
		Assert.assertTrue(response != null && BankApplicationConstants.SUCCESS.equals(response.getStatus()));
	}
	
	//@Test
	public void userLoginTest() {
		Response response = userService.userLogin("jamesisauser@gmail.com", "james@user", null);
		System.out.println(response.getErrorMessage());
		Assert.assertTrue(response != null && BankApplicationConstants.SUCCESS.equals(response.getStatus()));
	}
	
	//@Test
	public void changePasswordTest() {
		Response response = userService.changePassword(1l, "james@user", "james@newuser");
		System.out.println(response.getErrorMessage());
		Assert.assertTrue(response != null && BankApplicationConstants.SUCCESS.equals(response.getStatus()));
	}
	
	//@Test
	public void updateContactDetailsTest() {
		User user = new User();
		user.setUserId(1l);
		user.setAddress("Newyork wall street");
		Response response = userService.updateContactDetails(user);
		System.out.println(response.getErrorMessage());
		Assert.assertTrue(response != null && BankApplicationConstants.SUCCESS.equals(response.getStatus()));
	}
	
	//@Test
	public void getUserDetailsTest() {
		User user = new User();
		user.setUserId(1l);
		Response response = userService.getUserDetails(user);
		System.out.println(response.getData());
		Assert.assertTrue(response != null && BankApplicationConstants.SUCCESS.equals(response.getStatus()));
	}
	
}
