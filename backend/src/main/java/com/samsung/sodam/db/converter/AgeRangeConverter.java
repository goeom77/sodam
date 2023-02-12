package com.samsung.sodam.db.converter;

import com.samsung.sodam.db.entity.AGE_RANGE;

import javax.persistence.AttributeConverter;
import java.util.EnumSet;

public class AgeRangeConverter implements AttributeConverter<AGE_RANGE, Integer> {

    @Override
    public Integer convertToDatabaseColumn(AGE_RANGE attribute) {
        if(attribute != null) return attribute.getCode();
        return AGE_RANGE.NONE.getCode();
    }

    @Override
    public AGE_RANGE convertToEntityAttribute(Integer dbData) {
        return EnumSet.allOf(AGE_RANGE.class).stream()
                .filter(e->e.getCode().equals(dbData))
                .findAny()
                .orElse(AGE_RANGE.NONE);
    }
}
