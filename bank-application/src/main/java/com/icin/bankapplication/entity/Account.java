package com.icin.bankapplication.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.icin.bankapplication.constants.AccountStatus;
import com.icin.bankapplication.constants.AccountType;

import lombok.Data;

@Entity
@Table(name = "account")
@Data
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long accountId;
	@OneToOne
	@JoinColumn(name = "userId", referencedColumnName = "userId")
	private User user;
	private AccountStatus status;
	private AccountType accountType;
	private Double balance;
	private Date lastUpdated;	
}
