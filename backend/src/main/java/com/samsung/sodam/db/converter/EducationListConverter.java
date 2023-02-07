package com.samsung.sodam.db.converter;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.samsung.sodam.db.entity.Education;
import com.samsung.sodam.util.CounselorUtil;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Collections;
import java.util.List;

@Converter(autoApply = true)
public class EducationListConverter implements AttributeConverter<List<Education>, String> {
    private static final ObjectMapper mapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);

    @Override
    public String convertToDatabaseColumn(List<Education> attribute) {
        if (attribute == null || attribute.isEmpty()) return null;
        return CounselorUtil.educationObjectToString(attribute);
    }

    @Override
    public List<Education> convertToEntityAttribute(String dbData) {
        if(dbData == null || dbData.isEmpty()) return Collections.emptyList();
        return CounselorUtil.educationStringToObjectList(dbData);
    }
}