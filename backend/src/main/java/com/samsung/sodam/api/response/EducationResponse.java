package com.samsung.sodam.api.response;

import com.querydsl.core.annotations.QueryProjection;
import com.samsung.sodam.db.entity.ProfilePhoto;
import lombok.Data;

@Data
public class EducationResponse {
    private long id;
    private String degree;
    private String school;  // 학교
    private String major;  // 전공
    private String is_graduate; // 학업 상태 (재학, 졸업, 수료)
    private ProfilePhoto photo;

    @QueryProjection
    public EducationResponse(long id, String degree, String school, String major, String is_graduate, ProfilePhoto photo) {
        this.id = id;
        this.degree = degree;
        this.school = school;
        this.major = major;
        this.is_graduate = is_graduate;
        this.photo = photo;
    }
}
