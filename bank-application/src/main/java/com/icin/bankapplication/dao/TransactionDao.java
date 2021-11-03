package com.icin.bankapplication.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.icin.bankapplication.entity.Account;
import com.icin.bankapplication.entity.Transaction;

@Repository
public interface TransactionDao extends JpaRepository<Transaction, Long> {
	
	List<Transaction> findByToAccount(Account toAccount);
	
	List<Transaction> findByFromAccount(Account fromAccount);
	
	List<Transaction> findByFromAccountAndToAccount(Account fromAccount,Account toAccount);
	
	List<Transaction> findByFromAccountOrToAccount(Account fromAccount,Account toAccount);


}
