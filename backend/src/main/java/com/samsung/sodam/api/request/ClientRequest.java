package com.samsung.sodam.api.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.samsung.sodam.db.converter.AgeRangeConverter;
import com.samsung.sodam.db.converter.GenderConverter;
import com.samsung.sodam.db.entity.AGE_RANGE;
import com.samsung.sodam.db.entity.GENDER;
import lombok.*;

import javax.persistence.Convert;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ClientRequest {
    private String id;
    private String password;
    private String name;

    private String tel;
    private String email;

    private String profileImg;

    @Convert(converter = GenderConverter.class)
    private GENDER gender;

    @Convert(converter = AgeRangeConverter.class)
    private AGE_RANGE age;
}