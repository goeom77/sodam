package com.samsung.sodam.db.converter;

import javax.persistence.AttributeConverter;

/**
 * 상담사
 */
public class StateAttributeConverter implements AttributeConverter<String,Integer> {
    @Override
    public Integer convertToDatabaseColumn(String attribute) {
        if(attribute.equals("관리자")) return 0;
        else if(attribute.equals("상담사")) return 1;
        else if(attribute.equals("내담자")) return 2;
        else throw new RuntimeException();
    }

    @Override
    public String convertToEntityAttribute(Integer dbData) {
        if(dbData == 0) return "관리자";
        else if(dbData == 1) return "상담사";
        else if(dbData == 2) return "내담자";
        else throw new RuntimeException();
    }
}
