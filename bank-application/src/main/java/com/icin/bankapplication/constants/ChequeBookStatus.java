package com.icin.bankapplication.constants;

import java.util.stream.Stream;

public enum ChequeBookStatus {
	APPROVED("A"),
	REJECTED("R"),
	REQUESTED("I");
	
	private String value;
	
	ChequeBookStatus(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
    public static void main(String[] args)  {
    	
    ChequeBookStatus chequeBookStatus = ChequeBookStatus.APPROVED;
	
    System.out.println(chequeBookStatus.value);
    
 }
	public static ChequeBookStatus getStatus(String value) {
		return Stream.of(ChequeBookStatus.values()).filter(c -> c.getValue().equals(value)).findFirst().orElse(null);
	}
}
