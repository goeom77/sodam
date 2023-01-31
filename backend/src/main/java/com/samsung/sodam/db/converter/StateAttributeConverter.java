package com.samsung.sodam.db.converter;

import com.samsung.sodam.db.entity.STATE;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;

/**
 * 상담사
 */
@Converter(autoApply = true)
public class StateAttributeConverter implements AttributeConverter<STATE, Integer> {
//    @Override
//    protected STATE[] getValueList() {
//        return STATE.values();
//    }

    @Override
    public Integer convertToDatabaseColumn(STATE attribute) {
        return null;
    }

    @Override
    public STATE convertToEntityAttribute(Integer dbData) {
        return STATE.fromCode(dbData);
    }
}
