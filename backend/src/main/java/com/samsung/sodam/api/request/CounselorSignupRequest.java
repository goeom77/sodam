package com.samsung.sodam.api.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CounselorSignupRequest extends CounselorRequest{
    ArrayList<CertRequest> certificates;
    ArrayList<EduRequest> educations;
    //ArrayList<Career> careers;

    //@Convert(converter = CareerListConverter.class)
//    private ArrayList<Career> careers;
}
