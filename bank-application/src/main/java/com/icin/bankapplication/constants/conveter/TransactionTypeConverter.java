package com.icin.bankapplication.constants.conveter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.icin.bankapplication.constants.TransactionType;

@Converter(autoApply = true)
public class TransactionTypeConverter implements AttributeConverter<TransactionType, String> {

	public TransactionTypeConverter() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String convertToDatabaseColumn(TransactionType attribute) {
		return (attribute == null) ? null : attribute.getValue();
	}

	@Override
	public TransactionType convertToEntityAttribute(String dbData) {
		if (dbData == null) {
			return null;
		}
		return (dbData == null) ? null : TransactionType.getStatus(dbData);
	}

}
