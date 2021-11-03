package com.icin.bankapplication.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icin.bankapplication.BankException;
import com.icin.bankapplication.entity.Account;
import com.icin.bankapplication.entity.Response;
import com.icin.bankapplication.entity.Transaction;
import com.icin.bankapplication.entity.TransactionSearchCriteria;
import com.icin.bankapplication.service.TransactionService;

@Controller
public class TransactionController {
	
	@Autowired
	TransactionService transactionService;
	
	public TransactionController() {
		// TODO Auto-generated constructor stub
	}
	
	@PostMapping("/createTransaction")
	public @ResponseBody Response createTransaction(@RequestBody Transaction transaction) throws BankException {
		return transactionService.createTransaction(transaction);
	}
	
	@GetMapping("/getTransactionDetails")
	public @ResponseBody Response getTransactionDetails(@RequestBody TransactionSearchCriteria transactionSearchCriteria) {
		return transactionService.getTransactionDetails(transactionSearchCriteria);
	}

}
