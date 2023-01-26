package com.samsung.sodam.db.converter;

import com.samsung.sodam.db.entity.CATEGORY;

import javax.persistence.AttributeConverter;
import java.util.EnumSet;
import java.util.NoSuchElementException;

public class CategoryConverter implements AttributeConverter<CATEGORY, String> {

    @Override
    public String convertToDatabaseColumn(CATEGORY attribute) {
        return attribute.getCode();
    }

    @Override
    public CATEGORY convertToEntityAttribute(String dbData) {
        return EnumSet.allOf(CATEGORY.class).stream()
                .filter(e -> e.getCode().equals(dbData))
                .findAny()
                .orElseThrow(() -> new NoSuchElementException());
    }
}
