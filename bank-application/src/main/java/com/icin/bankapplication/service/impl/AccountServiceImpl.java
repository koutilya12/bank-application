package com.icin.bankapplication.service.impl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.icin.bankapplication.constants.AccountStatus;
import com.icin.bankapplication.constants.BankApplicationConstants;
import com.icin.bankapplication.constants.helpers.Validator;
import com.icin.bankapplication.dao.AccountDao;
import com.icin.bankapplication.entity.Account;
import com.icin.bankapplication.entity.Response;
import com.icin.bankapplication.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService{
	
	@Autowired
	AccountDao accountDao;
	
	@Override
	public Response createAccount(Account account) {
		String errorMessage = Validator.validateAccount(account);
		if(errorMessage != null) {
			return new Response(BankApplicationConstants.FAILED, errorMessage);
		}
		accountDao.save(account);
		if(account.getAccountId() != null) {
			return new Response(BankApplicationConstants.SUCCESS, "Account successfully created");
		}
		return new Response(BankApplicationConstants.FAILED, "Account creation failed");
	}

	@Transactional
	@Override
	public Response updateAccountStatus(Long accountId, AccountStatus status) {
		String errorMessage = Validator.validateAccountStatus(accountId, status);
		if(errorMessage != null) {
			return new Response(BankApplicationConstants.FAILED, errorMessage);
		}
		if(accountDao.updateAccountStatus(accountId, status, new Date()) != 0) {
			return new Response(BankApplicationConstants.SUCCESS, "Account status successfully updated");
		}
		return new Response(BankApplicationConstants.FAILED, "update Account status failed");
	}

	@Transactional
	@Override
	public Response updateAccountBalance(Long id, Double amount) {
		String errorMessage = Validator.validateUpdateAccountBalance(id, amount);
		if(errorMessage != null) {
			return new Response(BankApplicationConstants.FAILED, errorMessage);
		}
		if(accountDao.updateAccountBalance(id, amount, new Date()) != 0) {
			return new Response(BankApplicationConstants.SUCCESS, "Account balance updated");
		}
		return new Response(BankApplicationConstants.FAILED, "update account balance failed");
	}

	@Override
	public Response getAccountDetails(Account account) {
		String errorMessage = Validator.validateAccountSearchCriteria(account);
		if(errorMessage != null) {
			return new Response(BankApplicationConstants.FAILED, errorMessage);
		}	
		Example<Account>  example = Example.of(account);
		List<Account> accountDetails = accountDao.findAll(example);
		if(accountDetails != null && !accountDetails.isEmpty()) {
			return new Response(BankApplicationConstants.SUCCESS, null,accountDetails);
		}
		return new Response(BankApplicationConstants.FAILED, "Search account details failed");
	}

}
