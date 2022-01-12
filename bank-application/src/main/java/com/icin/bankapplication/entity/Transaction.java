
package com.icin.bankapplication.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.icin.bankapplication.constants.TransactionStatus;
import com.icin.bankapplication.constants.TransactionType;

import lombok.Data;

@Entity
@Table(name = "transaction_history")
@Data
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long transactionId;
	
	@OneToOne
	@JoinColumn(name = "fromAccount", referencedColumnName = "accountId")
	private Account fromAccount;
	@OneToOne
	@JoinColumn(name = "toAccount", referencedColumnName = "accountId")
	private Account toAccount;
	private Double transactionAmount;
	private TransactionType transactionType;
	private Date transactionTime;
	private TransactionStatus status;
	private String createdBy;
}
