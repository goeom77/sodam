package com.samsung.sodam.db.converter;

import com.samsung.sodam.db.entity.STATE;

import javax.persistence.Converter;

/**
 * 상담사
 */
@Converter
public class StateAttributeConverter extends EntityConverter<STATE, Integer,String> {
    @Override
    protected STATE[] getValueList() {
        return STATE.values();
    }

    @Override
    protected String[] getNameList() {
        return new String[0];
    }
}
