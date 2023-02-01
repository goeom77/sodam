package com.samsung.sodam.db.converter;

import com.samsung.sodam.db.entity.GENDER;

import javax.persistence.Converter;

/**
 * Men : "M"
 * WOMEN : "W"
 */
@Converter(autoApply = true)
public class GenderConverter extends EntityConverter<GENDER, String> {
    @Override
    protected GENDER[] getValueList() {
        return GENDER.values();
    }
}
