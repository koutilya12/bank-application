package com.icin.bankapplication.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
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

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
public class AccountController {
	
	@Autowired
	AccountService accountService;

	
	@PostMapping("/createAccount")
	@PreAuthorize("hasRole('CUSTOMER')")
	public @ResponseBody Response createAccount(@RequestBody Account account) {
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
	
	@GetMapping("/getAccountDetails")
	@PreAuthorize("hasRole('CUSTOMER') or hasRole('ADMIN')")
	public @ResponseBody Response getAccountDetails(@RequestBody Account account) {
		return accountService.getAccountDetails(account);
	}
}
