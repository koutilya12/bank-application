package com.icin.bankapplication.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.icin.bankapplication.constants.ChequeBookStatus;

import lombok.Data;

@Entity
@Table(name = "cheque_books")
@Data
public class ChequeBookRequest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long chequeBookId;
	@OneToOne
	@JoinColumn(name = "accountId", referencedColumnName = "accountId")
	private Account account;
	private ChequeBookStatus status;
	private String createdBy;
}
