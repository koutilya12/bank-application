package com.icin.bankapplication.constants;

import java.util.stream.Stream;

public enum TransactionStatus {
	APPROVED("A");
	
	private String value;
	
	TransactionStatus(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
    public static void main(String[] args)  {
    	
    TransactionStatus transactionStatus = TransactionStatus.APPROVED;
	
    System.out.println(transactionStatus.value);
    
 }
	public static TransactionStatus getStatus(String value) {
		return Stream.of(TransactionStatus.values()).filter(c -> c.getValue().equals(value)).findFirst().orElse(null);
	}
}
