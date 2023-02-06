package com.samsung.sodam.db.converter;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.samsung.sodam.db.entity.CONSULT_TYPE;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.*;
import java.util.stream.Collectors;

@Converter(autoApply = true)
public class ConsultListConverter implements AttributeConverter<List<CONSULT_TYPE>, List<Integer>> {
    private static final ObjectMapper mapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);

    @Override
    public List<Integer> convertToDatabaseColumn(List<CONSULT_TYPE> attribute) {
        if (attribute == null || attribute.isEmpty()) return null;
        return attribute.stream().map(CONSULT_TYPE::getValue).collect(Collectors.toList());
    }

    @Override
    public List<CONSULT_TYPE> convertToEntityAttribute(List<Integer> dbData) {
        if(dbData == null || dbData.isEmpty()) return Collections.emptyList();
        return dbData.stream().map(integer -> Arrays.stream(CONSULT_TYPE.values())
                .filter(val -> Objects.equals(val.getValue(), integer))
                .findFirst().orElseThrow()).collect(Collectors.toList());
    }


}