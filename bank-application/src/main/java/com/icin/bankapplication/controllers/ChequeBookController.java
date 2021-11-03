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
import com.icin.bankapplication.constants.ChequeBookStatus;
import com.icin.bankapplication.entity.ChequeBookRequest;
import com.icin.bankapplication.entity.Response;
import com.icin.bankapplication.service.ChequeBookService;

@Controller
public class ChequeBookController {
	
	@Autowired
	ChequeBookService chequeBookService;
	
	public ChequeBookController() {
		// TODO Auto-generated constructor stub
	}
	
	@PostMapping("/createChequeBookRequest")
	public @ResponseBody Response createChequeBookRequest(@RequestBody ChequeBookRequest chequeBookRequest) {
		return chequeBookService.createChequeBookRequest(chequeBookRequest);
	}
	
	@PutMapping("/updateChequeBookStatus")
	public @ResponseBody Response createChequeBookRequest(@RequestParam Long chequeBookId, @RequestParam ChequeBookStatus status) {
		return chequeBookService.updateChequeBookStatus(chequeBookId, status);
	}
	
	@GetMapping("/getChequeBookDetails")
	public @ResponseBody Response getChequeBookDetails(@RequestBody ChequeBookRequest chequeBookRequest) {
		return chequeBookService.getChequeBookDetails(chequeBookRequest);
	}

}
