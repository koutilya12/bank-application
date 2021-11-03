package com.icin.bankapplication.constants.conveter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.icin.bankapplication.constants.UserType;

@Converter(autoApply = true)
public class UserTypeConverter implements AttributeConverter<UserType, String> {

	public UserTypeConverter() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String convertToDatabaseColumn(UserType attribute) {
		return (attribute == null) ? null : attribute.getValue();
	}

	@Override
	public UserType convertToEntityAttribute(String dbData) {
		if (dbData == null) {
			return null;
		}
		return (dbData == null) ? null : UserType.getStatus(dbData);
	}

}
