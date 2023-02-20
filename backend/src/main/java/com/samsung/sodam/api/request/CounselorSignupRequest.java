package com.samsung.sodam.api.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@EqualsAndHashCode(callSuper=false)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CounselorSignupRequest extends CounselorRequest{
    ArrayList<CertRequest> certificates;
    ArrayList<EduRequest> educations;

    ArrayList<Long> cert_delete;
    ArrayList<Long> edu_delete;

    //ArrayList<Career> careers;

    //@Convert(converter = CareerListConverter.class)
//    private ArrayList<Career> careers;
}
