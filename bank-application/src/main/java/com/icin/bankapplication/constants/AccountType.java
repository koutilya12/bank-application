package com.icin.bankapplication.constants;

import java.util.stream.Stream;

public enum AccountType {
	SAVINGS("S"),
	CURRENT("C");
	
	private String value;
	
	AccountType(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
    public static void main(String[] args)  {
    	
    AccountType accountType = AccountType.SAVINGS;
	
    System.out.println(accountType.value);
    
 }
	public static AccountType getStatus(String value) {
		return Stream.of(AccountType.values()).filter(c -> c.getValue().equals(value)).findFirst().orElse(null);
	}
}
