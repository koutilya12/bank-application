package com.icin.bankapplication.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icin.bankapplication.BankException;
import com.icin.bankapplication.constants.BankApplicationConstants;
import com.icin.bankapplication.constants.TransactionStatus;
import com.icin.bankapplication.constants.TransactionType;
import com.icin.bankapplication.constants.helpers.Validator;
import com.icin.bankapplication.dao.AccountDao;
import com.icin.bankapplication.dao.TransactionDao;
import com.icin.bankapplication.entity.Response;
import com.icin.bankapplication.entity.Transaction;
import com.icin.bankapplication.entity.TransactionSearchCriteria;
import com.icin.bankapplication.service.AccountService;
import com.icin.bankapplication.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {
	
	@Autowired
	TransactionDao transactionDao;
	
	@Autowired
	AccountDao accountDao;
	
	@Autowired
	AccountService accountService;
	
	@Override
	@Transactional(rollbackOn = BankException.class)
	public Response createTransaction(Transaction transaction) throws BankException {
		String errorMessage = Validator.validateTransaction(transaction);
		if(errorMessage != null) {
			return new Response(BankApplicationConstants.FAILED, errorMessage);
		}
		Response response = validateAndUpdateTransactionType(transaction);
		if(response.getStatus().equals(BankApplicationConstants.FAILED)) {
			return new Response(BankApplicationConstants.FAILED, response.getErrorMessage());
		}
		transaction.setStatus(TransactionStatus.APPROVED);
		transactionDao.save(transaction);
		if(transaction.getTransactionId() == null) {
			throw new BankException("Transaction failed");
		}
		return new Response(BankApplicationConstants.SUCCESS, "Transaction successful");
	}

	private Response validateAndUpdateTransactionType(Transaction transaction) throws BankException  {
		switch(transaction.getTransactionType()) {
		case DEPOSIT    :
			return depositAmount(transaction);
		case WITHDRAWAL :
			return withdrawAmount(transaction);
		case TRANSFER   : 
			return transferAmount(transaction);
		}
		return new Response(BankApplicationConstants.FAILED, "Invalid transaction type");	
	}

	private Response depositAmount(Transaction transaction) {
		if(transaction.getFromAccount() != null) {
			return new Response(BankApplicationConstants.FAILED, "Wrong transaction type");
		}
		if(!accountDao.existsById(transaction.getToAccount().getAccountId())) {
			return new Response(BankApplicationConstants.FAILED, "Invalid To Account");
		}
		return accountService.updateAccountBalance(transaction.getToAccount().getAccountId(), transaction.getTransactionAmount());
	}
	
	private Response withdrawAmount(Transaction transaction) {
		if(transaction.getToAccount() != null) {
			return new Response(BankApplicationConstants.FAILED, "Wrong transaction type");
		}
		if(!accountDao.existsById(transaction.getFromAccount().getAccountId())) {
			return new Response(BankApplicationConstants.FAILED, "Invalid From Account");
		}
		if(accountDao.validateTransactionAmount(transaction.getFromAccount().getAccountId(), transaction.getTransactionAmount()) != null) {
		return accountService.updateAccountBalance(transaction.getFromAccount().getAccountId(), -1 * transaction.getTransactionAmount());
		}
		return new Response(BankApplicationConstants.FAILED, "Insufficient funds"); 
 	}
	
	private Response transferAmount(Transaction transaction) throws BankException {
		if(!accountDao.existsById(transaction.getFromAccount().getAccountId())) {
			return new Response(BankApplicationConstants.FAILED, "Invalid from Account");
		}
		if(!accountDao.existsById(transaction.getToAccount().getAccountId())) {
			return new Response(BankApplicationConstants.FAILED, "Invalid to Account");
		}
		if(accountDao.validateTransactionAmount(transaction.getFromAccount().getAccountId(), transaction.getTransactionAmount()) == null) {
			return new Response(BankApplicationConstants.FAILED, "Insufficient funds");
		}
		if(accountService.updateAccountBalance(transaction.getFromAccount().getAccountId(), -1 * transaction.getTransactionAmount()).getStatus().equals(BankApplicationConstants.FAILED)) {
			return new Response(BankApplicationConstants.FAILED, "from account update failed");
		} 
		if(accountService.updateAccountBalance(transaction.getToAccount().getAccountId(), transaction.getTransactionAmount()).getStatus().equals(BankApplicationConstants.FAILED)) {
			throw new BankException("to account update failed");
		}
		return new Response(BankApplicationConstants.SUCCESS);
	}
	
	@Override
	public Response getTransactionDetails(TransactionSearchCriteria transactionSearchCriteria) {
		String errorMessage = Validator.validateTransactionDetails(transactionSearchCriteria);
		if(errorMessage != null) {
			return new Response(BankApplicationConstants.FAILED, errorMessage);
		}
		List<Transaction> transactionValues = validateAndGetTransactionDetails(transactionSearchCriteria);
		errorMessage = validateTransactionValues(transactionValues);
		if(errorMessage != null) {
			return new Response(BankApplicationConstants.FAILED, errorMessage);
		}
		return new Response(BankApplicationConstants.SUCCESS, transactionValues);
	}

	private String validateTransactionValues(List<Transaction> transactionDetails) {
		if(transactionDetails == null) {
			return "empty transaction details";
		}
		if(transactionDetails.isEmpty()) {
			return "transaction details list empty";
		}
		return null;
	}

	private List<Transaction> validateAndGetTransactionDetails(TransactionSearchCriteria transactionSearchCriteria) {
		switch(transactionSearchCriteria.getTransactionType()) {
		case DEPOSIT    :
		    return transactionDao.findByToAccount(transactionSearchCriteria.getToAccount());
		case WITHDRAWAL : 
		    return transactionDao.findByFromAccount(transactionSearchCriteria.getFromAccount());
		case TRANSFER   :
		    return transactionDao.findByFromAccountAndToAccount(transactionSearchCriteria.getFromAccount(), transactionSearchCriteria.getToAccount());
	    default         :
			return transactionDao.findByFromAccountOrToAccount(transactionSearchCriteria.getFromAccount(), transactionSearchCriteria.getToAccount());
		}
	}
}
