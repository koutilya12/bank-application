package com.icin.bankapplication.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icin.bankapplication.BankException;
import com.icin.bankapplication.entity.Response;
import com.icin.bankapplication.entity.Transaction;
import com.icin.bankapplication.entity.TransactionSearchCriteria;
import com.icin.bankapplication.service.TransactionService;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
public class TransactionController {
	
	@Autowired
	TransactionService transactionService;
	
	
	@PostMapping("/createTransaction")
	@PreAuthorize("hasRole('CUSTOMER')")
	public @ResponseBody Response createTransaction(@RequestBody Transaction transaction) throws BankException {
		return transactionService.createTransaction(transaction);
	}
	
	@GetMapping("/getTransactionDetails")
	@PreAuthorize("hasRole('CUSTOMER') or hasRole('ADMIN')")
	public @ResponseBody Response getTransactionDetails(@RequestBody TransactionSearchCriteria transactionSearchCriteria) {
		return transactionService.getTransactionDetails(transactionSearchCriteria);
	}

}
