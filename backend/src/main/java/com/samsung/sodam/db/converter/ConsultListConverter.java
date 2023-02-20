package com.samsung.sodam.db.converter;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.samsung.sodam.db.entity.CONSULT_TYPE;
import com.samsung.sodam.util.ListDBUtil;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.*;
import java.util.stream.Collectors;

@Converter(autoApply = true)
public class ConsultListConverter implements AttributeConverter<List<CONSULT_TYPE>, String> {
    private static final ObjectMapper mapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);

    @Override
    public String convertToDatabaseColumn(List<CONSULT_TYPE> attribute) {
        if (attribute == null || attribute.isEmpty()) return null;
        List<Integer> list = attribute.stream().map(CONSULT_TYPE::getValue).collect(Collectors.toList());
        return new Gson().toJson(list);
    }

    @Override
    public List<CONSULT_TYPE> convertToEntityAttribute(String dbData) {
        if(dbData == null || dbData.isEmpty()) return Collections.emptyList();
        List<Integer> list = ListDBUtil.DBDataToIntegerList(dbData);
        return list.stream().map(integer -> Arrays.stream(CONSULT_TYPE.values())
                .filter(val -> Objects.equals(val.getValue(), integer))
                .findFirst().orElseThrow()).collect(Collectors.toList());
    }


}