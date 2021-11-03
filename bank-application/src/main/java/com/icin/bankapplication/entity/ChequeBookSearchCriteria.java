package com.icin.bankapplication.entity;

import com.icin.bankapplication.constants.ChequeBookStatus;

import lombok.Data;

@Data
public class ChequeBookSearchCriteria {
	Long chequeBookId;
	Long userId;
	ChequeBookStatus status;
}
