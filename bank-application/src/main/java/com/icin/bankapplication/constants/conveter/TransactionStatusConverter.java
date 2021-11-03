package com.icin.bankapplication.constants.conveter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.icin.bankapplication.constants.TransactionStatus;

@Converter(autoApply = true)
public class TransactionStatusConverter implements AttributeConverter<TransactionStatus, String> {

	public TransactionStatusConverter() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String convertToDatabaseColumn(TransactionStatus attribute) {
		return (attribute == null) ? null : attribute.getValue();
	}

	@Override
	public TransactionStatus convertToEntityAttribute(String dbData) {
		if (dbData == null) {
			return null;
		}
		return (dbData == null) ? null : TransactionStatus.getStatus(dbData);
	}

}
