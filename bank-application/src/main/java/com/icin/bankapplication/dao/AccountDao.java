package com.icin.bankapplication.dao;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.icin.bankapplication.constants.AccountStatus;
import com.icin.bankapplication.entity.Account;

@Repository
public interface AccountDao extends JpaRepository<Account, Long> {
	
	@Modifying
	@Query("UPDATE Account a set a.status = :status, a.lastUpdated = :date WHERE a.accountId = :accountId")
	int updateAccountStatus(Long accountId, AccountStatus status, Date date);
	
	@Modifying
	@Query("UPDATE Account a set a.balance = a.balance + :amount, a.lastUpdated = :date WHERE a.accountId = :id")
	int updateAccountBalance(Long id, Double amount, Date date);
	
	@Query("SELECT a FROM Account a WHERE a.accountId = :accountId AND a.balance >= :transactionAmount")
	Account validateTransactionAmount(Long accountId, Double transactionAmount);

}
