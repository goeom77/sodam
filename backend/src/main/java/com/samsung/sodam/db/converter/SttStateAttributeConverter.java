package com.samsung.sodam.db.converter;

import com.samsung.sodam.db.entity.STT_STATE;

import javax.persistence.Converter;
import java.util.Arrays;
import java.util.stream.Collectors;

@Converter
public class SttStateAttributeConverter extends EntityConverter<STT_STATE, Integer, String> {
    @Override
    protected STT_STATE[] getValueList() {
        return STT_STATE.values();
    }

    @Override
    protected String[] getNameList() {
        String[] str = new String[0];
        Arrays.stream(STT_STATE.values()).map(it -> it.getName()).collect(Collectors.toList()).toArray(str);
        return str;
    }

    @Override
    public Integer convertToDatabaseColumn(STT_STATE attribute) {
        if (attribute != null)
            return super.convertToDatabaseColumn(attribute);
        return STT_STATE.WAIT.getValue();
    }

    @Override
    public STT_STATE convertToEntityAttribute(Integer dbData) {
        if (dbData != null && !dbData.equals(""))
            return super.convertToEntityAttribute(dbData);
        return STT_STATE.WAIT;
    }
}