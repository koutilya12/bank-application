package com.icin.bankapplication.entity;


import com.icin.bankapplication.constants.TransactionType;

import lombok.Data;

@Data
public class TransactionSearchCriteria {
	Account fromAccount;
	Account toAccount;
	TransactionType transactionType;
}
