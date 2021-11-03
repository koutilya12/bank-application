package com.icin.bankapplication.service;

import com.icin.bankapplication.constants.ChequeBookStatus;
import com.icin.bankapplication.entity.ChequeBookRequest;
import com.icin.bankapplication.entity.ChequeBookSearchCriteria;
import com.icin.bankapplication.entity.Response;

public interface ChequeBookService {
	/**
	 * 
	 * @param chequeBookRequest
	 * @return
	 */
	public Response createChequeBookRequest(ChequeBookRequest chequeBookRequest);
	/**
	 * 
	 * @param chequeBookId
	 * @param status
	 * @return
	 */
	public Response updateChequeBookStatus(Long chequeBookId, ChequeBookStatus status);
	/**
	 * 
	 * @param chequeBookSearchCriteria
	 * @return
	 */
	public Response getChequeBookDetails(ChequeBookRequest chequeBookRequest); 
}
