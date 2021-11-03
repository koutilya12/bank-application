package com.icin.bankapplication.service;

import com.icin.bankapplication.BankException;
import com.icin.bankapplication.entity.Response;
import com.icin.bankapplication.entity.Transaction;
import com.icin.bankapplication.entity.TransactionSearchCriteria;

public interface TransactionService {
	/**
	 * 
	 * @param transaction
	 * @return
	 * @throws BankException 
	 */
	public Response createTransaction(Transaction transaction) throws BankException;
	/**
	 * 
	 * @param transactionSearchCriteria
	 * @return
	 */
	public Response getTransactionDetails(TransactionSearchCriteria transactionSearchCriteria);
}
