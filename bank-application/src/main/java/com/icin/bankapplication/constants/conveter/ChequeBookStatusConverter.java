package com.icin.bankapplication.constants.conveter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.icin.bankapplication.constants.ChequeBookStatus;

@Converter(autoApply = true)
public class ChequeBookStatusConverter implements AttributeConverter<ChequeBookStatus, String> {

	public ChequeBookStatusConverter() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String convertToDatabaseColumn(ChequeBookStatus attribute) {
		return (attribute == null) ? null : attribute.getValue();
	}

	@Override
	public ChequeBookStatus convertToEntityAttribute(String dbData) {
		if (dbData == null) {
			return null;
		}
		return (dbData == null) ? null : ChequeBookStatus.getStatus(dbData);
	}
}
