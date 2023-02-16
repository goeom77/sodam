package com.samsung.sodam.api.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.QueryProjection;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class ReviewResponse {

    Long id;
    String contents;
    Double stars;
    Integer pastCount=0;
    String type="ALL";
    String clientId;
    String counselorId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    LocalDateTime createdAt;
    String title;
    boolean myReview;

    @QueryProjection
    public ReviewResponse(Long id, String title, String contents, Double stars, Integer pastCount, String type,
                          String clientId, String counselorId, LocalDateTime createdAt, boolean myReview) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.stars = stars;
        this.pastCount = pastCount;
        this.type = type;
        this.clientId = clientId;
        this.counselorId = counselorId;
        this.createdAt = createdAt;
        this.myReview = myReview;
    }
}
