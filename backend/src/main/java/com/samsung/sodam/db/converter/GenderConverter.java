package com.samsung.sodam.db.converter;

import com.samsung.sodam.db.entity.GENDER;

import javax.persistence.Converter;

/**
 * Men : "M"
 * WOMEN : "W"
 */
@Converter(autoApply = true)
public class GenderConverter extends EntityConverter<GENDER, String,String> {
    @Override
    protected String[] getNameList() {
        return new String[0];
    }

    @Override
    protected GENDER[] getValueList() {
        return GENDER.values();
    }

    @Override
    public String convertToDatabaseColumn(GENDER attribute) {
        if(attribute != null)
            return super.convertToDatabaseColumn(attribute);
        return GENDER.NONE.getValue();
    }

    @Override
    public GENDER convertToEntityAttribute(String dbData) {
        if(dbData != null && !dbData.equals(""))
            return super.convertToEntityAttribute(dbData);
        return GENDER.NONE;
    }
}
