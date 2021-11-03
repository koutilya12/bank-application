package com.icin.bankapplication.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.icin.bankapplication.constants.ChequeBookStatus;
import com.icin.bankapplication.entity.ChequeBookRequest;

@Repository
public interface ChequeBookRequestDao extends JpaRepository<ChequeBookRequest, Long> {
	
	@Modifying
	@Query("UPDATE ChequeBookRequest cb set cb.status = :status WHERE cb.chequeBookId = :chequeBookId")
	int updateChequeBookStatus(Long chequeBookId, ChequeBookStatus status);

}
