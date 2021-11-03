package com.icin.bankapplication.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icin.bankapplication.constants.AccountStatus;
import com.icin.bankapplication.entity.Account;
import com.icin.bankapplication.entity.Response;
import com.icin.bankapplication.service.AccountService;

@Controller
public class AccountController {
	
	@Autowired
	AccountService accountService;
	
	public AccountController() {
		// TODO Auto-generated constructor stub
	}
	
	@PostMapping("/createAccount")
	public @ResponseBody Response createAccount(@RequestBody Account account) {
		return accountService.createAccount(account);
	}	
	
	@PutMapping("/updateAccountStatus")
	public @ResponseBody Response updateAccountStatus(@RequestParam Long accountId, @RequestParam AccountStatus status ) {
		return accountService.updateAccountStatus(accountId, status);
	}
	
	@PutMapping("/updateAccountBalance")
	public @ResponseBody Response updateAccountBalance(@RequestParam Long id, @RequestParam Double amount) {
		return accountService.updateAccountBalance(id, amount);
	}
	
	@GetMapping("/getAccountDetails")
	public @ResponseBody Response getAccountDetails(@RequestBody Account account) {
		return accountService.getAccountDetails(account);
	}
}
