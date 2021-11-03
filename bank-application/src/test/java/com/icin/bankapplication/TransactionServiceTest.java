package com.icin.bankapplication;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.icin.bankapplication.constants.BankApplicationConstants;
import com.icin.bankapplication.constants.TransactionStatus;
import com.icin.bankapplication.constants.TransactionType;
import com.icin.bankapplication.entity.Account;
import com.icin.bankapplication.entity.Response;
import com.icin.bankapplication.entity.Transaction;
import com.icin.bankapplication.entity.TransactionSearchCriteria;
import com.icin.bankapplication.service.TransactionService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionServiceTest {
	
	@Autowired
	TransactionService transactionService;

	public TransactionServiceTest() {
		
		
	}
	
	//@Test
	public void createTransactionTest1() throws BankException {
		Transaction transaction = new Transaction();
//		Account account = new Account();
//		account.setAccountId(1l);
//		transaction.setToAccount(account);
//		transaction.setFromAccount(account);
		Account account1 = new Account();
		Account account2 = new Account();
		account1.setAccountId(4l);
		account2.setAccountId(5l);
		transaction.setToAccount(account1);
		transaction.setFromAccount(account2);
		transaction.setTransactionAmount(30000.00);
		transaction.setTransactionType(TransactionType.TRANSFER);
		transaction.setTransactionTime(new Date());
		transaction.setStatus(TransactionStatus.APPROVED);
		transaction.setCreatedBy("warner");
		
		Response response = transactionService.createTransaction(transaction);
		System.out.println(response.getStatus());
		System.out.println(response.getErrorMessage());
		
		Assert.assertTrue(response != null && BankApplicationConstants.SUCCESS.equals(response.getStatus()));
	} 
	
	//@Test
	public void createTransactionTest2() throws BankException {
		Transaction transaction = new Transaction();
//		Account account = new Account();
//		account.setAccountId(1l);
//		transaction.setToAccount(account);
//		transaction.setFromAccount(account);
		Account account1 = new Account();
		Account account2 = new Account();
		account1.setAccountId(4l);
		account2.setAccountId(5l);
		transaction.setToAccount(account1);
		transaction.setFromAccount(account2);
		transaction.setTransactionAmount(30000.00);
		transaction.setTransactionType(TransactionType.DEPOSIT);
		transaction.setTransactionTime(new Date());
		transaction.setStatus(TransactionStatus.APPROVED);
		transaction.setCreatedBy("warner");
		
		Response response = transactionService.createTransaction(transaction);
		System.out.println(response.getStatus());
		System.out.println(response.getErrorMessage());

		Assert.assertTrue(response != null && BankApplicationConstants.FAILED.equals(response.getStatus()));
	}
	
	//@Test
	public void getTransactionDetailsTest1() {
		TransactionSearchCriteria transactionSearchCriteria = new TransactionSearchCriteria();
		Account account = new Account();
		account.setAccountId(2l);		
		transactionSearchCriteria.setFromAccount(account);
//		transactionSearchCriteria.setTransactionType(TransactionType.WITHDRAWAL);
//		transactionSearchCriteria.setTransactionType(TransactionType.DEPOSIT);
		transactionSearchCriteria.setTransactionType(TransactionType.WITHDRAWAL);
		
		Response response = transactionService.getTransactionDetails(transactionSearchCriteria);
		System.out.println((List<Transaction>) response.getData());
		Assert.assertTrue(response != null && BankApplicationConstants.SUCCESS.equals(response.getStatus()));
	}
}

   



