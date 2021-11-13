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

import com.icin.bankapplication.constants.ChequeBookStatus;
import com.icin.bankapplication.entity.ChequeBookRequest;
import com.icin.bankapplication.entity.Response;
import com.icin.bankapplication.service.ChequeBookService;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
public class ChequeBookController {
	
	@Autowired
	ChequeBookService chequeBookService;
	
	
	@PostMapping("/createChequeBookRequest")
	@PreAuthorize("hasRole('CUSTOMER')")	
	public @ResponseBody Response createChequeBookRequest(@RequestBody ChequeBookRequest chequeBookRequest) {
		return chequeBookService.createChequeBookRequest(chequeBookRequest);
	}
	
	@PutMapping("/updateChequeBookStatus")
	@PreAuthorize("hasRole('ADMIN')")
	public @ResponseBody Response createChequeBookRequest(@RequestParam Long chequeBookId, @RequestParam ChequeBookStatus status) {
		return chequeBookService.updateChequeBookStatus(chequeBookId, status);
	}
	
	@GetMapping("/getChequeBookDetails")
	@PreAuthorize("hasRole('CUSTOMER') or hasRole('ADMIN')")
	public @ResponseBody Response getChequeBookDetails(@RequestBody ChequeBookRequest chequeBookRequest) {
		return chequeBookService.getChequeBookDetails(chequeBookRequest);
	}

}
