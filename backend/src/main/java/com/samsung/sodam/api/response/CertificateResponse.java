package com.samsung.sodam.api.response;

import com.querydsl.core.annotations.QueryProjection;
import com.samsung.sodam.db.entity.ProfilePhoto;
import lombok.Data;

@Data
public class CertificateResponse {
    private long id;
    private String name;
    private String serial_num;  // 일련번호
    private String agency;  // 기업명
    private ProfilePhoto photo;

    @QueryProjection
    public CertificateResponse(long id, String name, String serial_num, String agency, ProfilePhoto photo) {
        this.id = id;
        this.name = name;
        this.serial_num = serial_num;
        this.agency = agency;
        this.photo = photo;
    }
}
