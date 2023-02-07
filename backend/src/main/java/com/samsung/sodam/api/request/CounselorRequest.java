package com.samsung.sodam.api.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.samsung.sodam.db.converter.ConsultListConverter;
import com.samsung.sodam.db.converter.GenderConverter;
import com.samsung.sodam.db.entity.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Convert;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CounselorRequest {

    private String isKakaoRegister = "false";
    private String id;
    private String password;
    private String name;

    private String tel;
    private String email;


    private String introduce;
    private String major;
    @Convert(converter = GenderConverter.class)
    private GENDER gender;
    private String[] routine;
    private Role role = Role.COUNSELOR;

    private int enterpriseId;

    private String profileImg;

    private List<Education> educationRow;
    private List<Career> careerRow;

    //private List<String> consultType;
    //private Integer[] consultType;

    @Convert(converter = ConsultListConverter.class)
    private ArrayList<CONSULT_TYPE> consultType;
}