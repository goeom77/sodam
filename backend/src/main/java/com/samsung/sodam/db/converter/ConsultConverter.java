package com.samsung.sodam.db.converter;

import com.samsung.sodam.db.entity.CONSULT_TYPE;

import javax.persistence.Converter;

@Converter(autoApply = true)
public class ConsultConverter extends  EntityConverter<CONSULT_TYPE, Integer> {
    @Override
    protected CONSULT_TYPE[] getValueList() {
        return CONSULT_TYPE.values();
    }
}
