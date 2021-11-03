package com.icin.bankapplication.constants.conveter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.icin.bankapplication.constants.AccountStatus;

@Converter(autoApply = true)
public class AccountStatusConverter implements AttributeConverter<AccountStatus, String> {

	public AccountStatusConverter() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String convertToDatabaseColumn(AccountStatus attribute) {
		return (attribute == null) ? null : attribute.getValue();
	}

	@Override
	public AccountStatus convertToEntityAttribute(String dbData) {
		if (dbData == null) {
			return null;
		}
		return (dbData == null) ? null : AccountStatus.getStatus(dbData);
	}

}
