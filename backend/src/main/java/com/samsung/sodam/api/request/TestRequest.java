package com.samsung.sodam.api.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.samsung.sodam.db.converter.ConsultConverter;
import com.samsung.sodam.db.converter.ConsultListConverter;
import com.samsung.sodam.db.converter.GenderConverter;
import com.samsung.sodam.db.entity.CONSULT_TYPE;
import com.samsung.sodam.db.entity.GENDER;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Convert;
import javax.persistence.Embedded;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TestRequest {
    @Convert(converter = ConsultListConverter.class)
    private List<CONSULT_TYPE> list= Collections.emptyList();

    private String name;

    @Convert(converter = GenderConverter.class)
    private GENDER gender = GENDER.MEN;
}