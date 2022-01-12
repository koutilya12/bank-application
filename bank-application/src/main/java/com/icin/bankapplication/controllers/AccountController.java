package com.icin.bankapplication.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.icin.bankapplication.constants.AccountStatus;
import com.icin.bankapplication.constants.BankApplicationConstants;
import com.icin.bankapplication.entity.Account;
import com.icin.bankapplication.entity.Response;
import com.icin.bankapplication.entity.User;
import com.icin.bankapplication.service.AccountService;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
public class AccountController {
	
	@Autowired
	AccountService accountService;

	
	@PostMapping("/createAccount")
	//@PreAuthorize("hasRole('CUSTOMER')")
	public @ResponseBody Response createAccount(@RequestBody Account account) {
		account.setBalance(0.0);
		account.setLastUpdated(new Date());
		account.setStatus(AccountStatus.CREATED);
		return accountService.createAccount(account);
	}	
	
	@PutMapping("/updateAccountStatus")
	@PreAuthorize("hasRole('CUSTOMER') or hasRole('ADMIN')")
	public @ResponseBody Response updateAccountStatus(@RequestParam Long accountId, @RequestParam AccountStatus status ) {
		return accountService.updateAccountStatus(accountId, status);
	}
	
	@PutMapping("/updateAccountBalance")
	@PreAuthorize("hasRole('CUSTOMER')")
	public @ResponseBody Response updateAccountBalance(@RequestParam Long id, @RequestParam Double amount) {
		return accountService.updateAccountBalance(id, amount);
	}
	
	@GetMapping("/getAccountDetailsByUser/{userId}")
	public @ResponseBody Response getAccountDetails(@PathVariable String userId) {
		if(userId == null) {
			return new Response(BankApplicationConstants.FAILED, "UserId is empty");
		}
		return accountService.getAccountDetails(prepareAccountSearchCriteria(userId));
	}

	private Account prepareAccountSearchCriteria(String userId) {
		Account account = new Account();
		User user = new User();
		user.setUserId(Long.parseLong(userId));
		account.setUser(user);
		return account;
	}
}
