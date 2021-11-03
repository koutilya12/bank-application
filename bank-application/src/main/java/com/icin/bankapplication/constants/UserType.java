package com.icin.bankapplication.constants;

import java.util.stream.Stream;

public enum UserType {
	ADMIN("A"),
	CUSTOMER("C");
	
	private String value;
	
	UserType(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
    public static void main(String[] args)  {
    	
	UserType userType = UserType.CUSTOMER;
	
    System.out.println(userType.value);
    
 }
	public static UserType getStatus(String value) {
		return Stream.of(UserType.values()).filter(c -> c.getValue().equals(value)).findFirst().orElse(null);
	}
}
