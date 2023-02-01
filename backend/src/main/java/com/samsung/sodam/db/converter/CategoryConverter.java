package com.samsung.sodam.db.converter;

import com.samsung.sodam.db.entity.CATEGORY;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.NoSuchElementException;

public class CategoryConverter implements AttributeConverter<String, Integer> {

    @Override
    public Integer convertToDatabaseColumn(String attribute) {
        Integer code = Arrays.stream(CATEGORY.values())
                .filter(e -> e.getValue().equals(attribute))
                .findAny()
                .get()
                .getCode();

        if(code == null) new NoSuchElementException();
        return code;
    }

    @Override
    public String convertToEntityAttribute(Integer dbData) {
        String value = Arrays.stream(CATEGORY.values())
                .filter(e -> e.getCode().equals(dbData))
                .findAny()
                .get()
                .getValue();

        if(value == null) new NoSuchElementException();
        return value;
    }
}
