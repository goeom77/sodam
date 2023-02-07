package com.samsung.sodam.db.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.samsung.sodam.db.entity.CONSULT_TYPE;

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
        System.out.println("toJson:: "+list.toString());
        return new Gson().toJson(list);
    }

    @Override
    public List<CONSULT_TYPE> convertToEntityAttribute(String dbData) {
        if(dbData == null || dbData.isEmpty()) return Collections.emptyList();
        List<Integer> list = DBDataToIntegerList(dbData);
        List<CONSULT_TYPE> result = new ArrayList<>();
        System.out.println("convertToEntityAttribute :: "+list.toString());
        if(list.isEmpty()) return Collections.emptyList();
        list.stream().forEach(
                integer -> result.add(Arrays.stream(CONSULT_TYPE.values())
                        .filter(val -> val.getValue()== integer)
                        .findFirst().orElseThrow()
                )
        );
        return result;
    }

    public static List<Integer> DBDataToIntegerList(String dbData){
        System.out.println("DBDataToIntegerList :: "+dbData);
        List<Integer> list = new ArrayList<>();
        dbData = dbData.substring(1, dbData.length()-1);
        StringTokenizer st = new StringTokenizer(dbData, ",");
        while(st.hasMoreTokens()) list.add(Integer.parseInt(st.nextToken()));
        return list;
    }

}