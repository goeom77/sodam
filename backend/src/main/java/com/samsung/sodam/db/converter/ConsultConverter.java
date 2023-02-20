package com.samsung.sodam.db.converter;

import com.samsung.sodam.db.entity.CONSULT_TYPE;

import javax.persistence.Converter;
import java.util.Arrays;
import java.util.stream.Collectors;

@Converter(autoApply = true)
public class ConsultConverter extends EntityConverter<CONSULT_TYPE, Integer, String> {
    @Override
    protected CONSULT_TYPE[] getValueList() {
        return CONSULT_TYPE.values();
    }

    @Override
    protected String[] getNameList() {
        String[] str = new String[0];
        Arrays.stream(CONSULT_TYPE.values()).map(CONSULT_TYPE::getName).collect(Collectors.toList()).toArray(str);
        return str;
    }
}
