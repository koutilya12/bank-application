package com.icin.bankapplication.constants;

import java.util.stream.Stream;

public enum AccountStatus {
	ACTIVE("A"),
	INACTIVE("I"),
	CREATED("C");
	
	private String value;
	
	AccountStatus(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
    public static void main(String[] args)  {
    	
    AccountStatus accountStatus = AccountStatus.ACTIVE;
	
    System.out.println(accountStatus.value);
    
 }
	public static AccountStatus getStatus(String value) {
		return Stream.of(AccountStatus.values()).filter(c -> c.getValue().equals(value)).findFirst().orElse(null);
	}
}
