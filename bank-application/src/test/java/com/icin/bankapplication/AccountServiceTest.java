package com.icin.bankapplication;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.icin.bankapplication.constants.AccountStatus;
import com.icin.bankapplication.constants.AccountType;
import com.icin.bankapplication.constants.BankApplicationConstants;
import com.icin.bankapplication.entity.Account;
import com.icin.bankapplication.entity.Response;
import com.icin.bankapplication.entity.User;
import com.icin.bankapplication.service.AccountService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceTest {

	public AccountServiceTest() {

	}
	
	@Autowired
	AccountService accountService;
	
	//@Test
	public void createAccountTest() {
		Account account = new Account();
		User user = new User();
		user.setUserId(5l);
		account.setUser(user);
		account.setAccountType(AccountType.SAVINGS);
		account.setBalance(0.0);
		account.setStatus(AccountStatus.ACTIVE);
		account.setLastUpdated(new Date());
		
		Response response = accountService.createAccount(account);
		Assert.assertTrue(response != null && BankApplicationConstants.SUCCESS.equals(response.getStatus()));
	}
	
	//@Test
	public void getAccountDetailsTest() {
		Account account = new Account();
		User user = new User();
		//user.setUserId(2l);		
		//account.setAccountId(2l);
		//account.setBalance(14000.00);
		//account.setAccountType(AccountType.CURRENT);
		Response response = accountService.getAccountDetails(account);
		System.out.println(response.getData());
		Assert.assertTrue(response != null && BankApplicationConstants.SUCCESS.equals(response.getStatus()));
	}
}
