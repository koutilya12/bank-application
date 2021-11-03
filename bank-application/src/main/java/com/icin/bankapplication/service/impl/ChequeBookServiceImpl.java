package com.icin.bankapplication.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.icin.bankapplication.constants.BankApplicationConstants;
import com.icin.bankapplication.constants.ChequeBookStatus;
import com.icin.bankapplication.constants.helpers.Validator;
import com.icin.bankapplication.dao.AccountDao;
import com.icin.bankapplication.dao.ChequeBookRequestDao;
import com.icin.bankapplication.entity.ChequeBookRequest;
import com.icin.bankapplication.entity.ChequeBookSearchCriteria;
import com.icin.bankapplication.entity.Response;
import com.icin.bankapplication.entity.User;
import com.icin.bankapplication.service.ChequeBookService;

@Service
public class ChequeBookServiceImpl implements ChequeBookService {
	
	@Autowired
	ChequeBookRequestDao chequeBookRequestDao;
	
	@Autowired
	AccountDao accountDao;
	
	@Override
	public Response createChequeBookRequest(ChequeBookRequest chequeBookRequest) {
		String errorMessage = Validator.validateChequeBookRequest(chequeBookRequest);
		if(errorMessage != null) {
			return new Response(BankApplicationConstants.FAILED, errorMessage);
		}
		if(accountDao.existsById(chequeBookRequest.getAccount().getAccountId())) {
			chequeBookRequest.setStatus(ChequeBookStatus.REQUESTED);
			chequeBookRequestDao.save(chequeBookRequest);
		}
		if(chequeBookRequest.getChequeBookId() == null) {
			return new Response(BankApplicationConstants.FAILED, "cheque book request failed");
		}
		return new Response(BankApplicationConstants.SUCCESS, "cheque book request success");
	}

	@Transactional
	@Override
	public Response updateChequeBookStatus(Long chequeBookId, ChequeBookStatus status) {
		String errorMessage = Validator.validateChequeBookStatus(chequeBookId, status);
		if(errorMessage != null) {
			return new Response(BankApplicationConstants.FAILED, errorMessage);
		}
		if(chequeBookRequestDao.existsById(chequeBookId)) {
			if(chequeBookRequestDao.updateChequeBookStatus(chequeBookId, status) != 0) {
				return new Response(BankApplicationConstants.SUCCESS, "cheque book status updated successfully");
			}
			return new Response(BankApplicationConstants.FAILED, "cheque book status update failed");
		}
		return new Response(BankApplicationConstants.FAILED, "cheque book Id doesn't exist");
	}

	@Override
	public Response getChequeBookDetails(ChequeBookRequest chequeBookRequest) {
		String errorMessage = Validator.validateChequeBookSearch(chequeBookRequest);
		if(errorMessage != null) {
			return new Response(BankApplicationConstants.FAILED, errorMessage);
		}
		Example  example = Example.of(chequeBookRequest);
		List<ChequeBookRequest> chequeBookRequestDetails = chequeBookRequestDao.findAll(example);
		if(chequeBookRequestDetails != null && !chequeBookRequestDetails.isEmpty()) {
			return new Response(BankApplicationConstants.SUCCESS, chequeBookRequestDetails);
		}
		return new Response(BankApplicationConstants.FAILED, "cheque book details search failed");
	}
}
