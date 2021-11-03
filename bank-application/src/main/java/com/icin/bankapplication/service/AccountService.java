package com.icin.bankapplication.service;

import com.icin.bankapplication.constants.AccountStatus;
import com.icin.bankapplication.entity.Account;
import com.icin.bankapplication.entity.Response;

public interface AccountService {
	/**
	 * 
	 * @param account
	 * @return
	 */
	public Response createAccount(Account account);
	/**
	 * 
	 * @param accountId
	 * @param status
	 * @return
	 */
	public Response updateAccountStatus(Long accountId, AccountStatus status);
	/**
	 * 
	 * @param Id
	 * @param balance
	 * @return
	 */
	public Response updateAccountBalance(Long id, Double amount);
	/**
	 * 
	 * @param accountId
	 * @return
	 */
	public Response getAccountDetails(Account account);
}
