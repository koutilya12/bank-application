package com.icin.bankapplication;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.icin.bankapplication.constants.BankApplicationConstants;
import com.icin.bankapplication.constants.ChequeBookStatus;
import com.icin.bankapplication.dao.ChequeBookRequestDao;
import com.icin.bankapplication.entity.Account;
import com.icin.bankapplication.entity.ChequeBookRequest;
import com.icin.bankapplication.entity.Response;
import com.icin.bankapplication.service.ChequeBookService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChequeBookServiceTest {

	public ChequeBookServiceTest() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	ChequeBookRequestDao chequeBookRequestDao;
	
	@Autowired
	ChequeBookService chequeBookService;
	
	//@Test
	public void createChequeBookRequestTest() {
		ChequeBookRequest chequeBookRequest = new ChequeBookRequest();
		Account account = new Account();
		account.setAccountId(2l);
		chequeBookRequest.setAccount(account);
		chequeBookRequest.setCreatedBy("michael");
		Response response = chequeBookService.createChequeBookRequest(chequeBookRequest);
		Assert.assertTrue(response != null && BankApplicationConstants.SUCCESS.equals(response.getStatus()));
	}
	
	//@Test
	public void updateChequeBookStatusTest() {
		Response response = chequeBookService.updateChequeBookStatus(2l, ChequeBookStatus.APPROVED);
		response.getStatus();
		response.getErrorMessage();
		Assert.assertTrue(response != null && BankApplicationConstants.SUCCESS.equals(response.getStatus()));

	}
	
	// @Test
	public void getChequeBookDetailsTest() {
		ChequeBookRequest chequeBookRequest = new ChequeBookRequest();
		Account account = new Account();
		account.setAccountId(2l);
		chequeBookRequest.setAccount(account);	
		
		Response response = chequeBookService.getChequeBookDetails(chequeBookRequest);
		System.out.println(response.getData());
		Assert.assertTrue(response != null && BankApplicationConstants.SUCCESS.equals(response.getStatus()));
	}
}
