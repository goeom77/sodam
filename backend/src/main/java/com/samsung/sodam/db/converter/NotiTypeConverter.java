package com.samsung.sodam.db.converter;

import com.samsung.sodam.db.entity.NotificationType;

import javax.persistence.AttributeConverter;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.NoSuchElementException;

public class NotiTypeConverter implements AttributeConverter<NotificationType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(NotificationType attribute) {
        return attribute.getCode();
    }

    @Override
    public NotificationType convertToEntityAttribute(Integer dbData) {
        return EnumSet.allOf(NotificationType.class).stream()
                .filter(e->e.getCode().equals(dbData))
                .findAny()
                .orElse(null);
    }
}
