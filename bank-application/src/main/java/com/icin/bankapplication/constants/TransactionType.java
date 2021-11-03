package com.icin.bankapplication.constants;

import java.util.stream.Stream;

public enum TransactionType {
	DEPOSIT("D"),
	WITHDRAWAL("W"),
	TRANSFER("T");
	
	private String value;
	
	TransactionType(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
    public static void main(String[] args)  {
    	
	TransactionType transactionType = TransactionType.DEPOSIT;
	
    System.out.println(transactionType.value);
    
 }
	public static TransactionType getStatus(String value) {
		return Stream.of(TransactionType.values()).filter(c -> c.getValue().equals(value)).findFirst().orElse(null);
	}
}
