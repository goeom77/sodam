package com.samsung.sodam.api.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.samsung.sodam.db.converter.ConsultConverter;
import com.samsung.sodam.db.converter.ConsultListConverter;
import com.samsung.sodam.db.entity.CONSULT_TYPE;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Convert;
import javax.persistence.Embedded;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TestRequest {
    @Convert(converter = ConsultListConverter.class)
    private Set<CONSULT_TYPE> list;
}