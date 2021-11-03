package com.icin.bankapplication.constants.conveter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.icin.bankapplication.constants.AccountType;

@Converter(autoApply = true)
public class AccountTypeConverter implements AttributeConverter<AccountType, String> {

	public AccountTypeConverter() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String convertToDatabaseColumn(AccountType attribute) {
		return (attribute == null) ? null : attribute.getValue();
	}

	@Override
	public AccountType convertToEntityAttribute(String dbData) {
		if (dbData == null) {
			return null;
		}
		return (dbData == null) ? null : AccountType.getStatus(dbData);
	}

}
