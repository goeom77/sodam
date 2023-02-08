package com.samsung.sodam.api.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CounselorDetailResponse extends CounselorListResponse{
    private List<EducationResponse> education;
    private List<CertificateResponse> certificate;

    public CounselorDetailResponse(CounselorListResponse parent) {
        setName(parent.getName());
        setTel(parent.getTel());
        setEmail(parent.getEmail());
        setId(parent.getId());
        setIntroduce(parent.getIntroduce());
        setGender(parent.getGender());
        setRoutine(parent.getRoutine());
        setProfileImg(parent.getProfileImg());
        setConsultTypeList(parent.getConsultTypeList());
        setCareer(parent.getCareer());
    }
}
