package com.samsung.sodam.db.entity;

import com.querydsl.core.annotations.QueryProjection;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;


@Table
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @Id
    @Column(name = "review_id")
    @GeneratedValue
    Long id;

    @Column(length = 500)
    String contents;
    @ApiModelProperty(required = true)
    Double stars;
    @Column(name = "past_count")
    Integer pastCount=0;

    String type="ALL";
    @Column(name = "client_id")
    @ApiModelProperty(required = true)
    String clientId;

    @Column(name = "counselor_Id")
    @ApiModelProperty(required = true)
    String counselorId;

    @Column(name = "created_at")
    LocalDateTime createdAt;

    @ApiModelProperty(required = true)
    String title;

    @QueryProjection
    public Review(Long id, String contents, Double stars, Integer pastCount, String type, String clientId, String counselorId, LocalDateTime createdAt) {
        this.id = id;
        this.contents = contents;
        this.stars = stars;
        this.pastCount = pastCount;
        this.type = type;
        this.clientId = clientId;
        this.counselorId = counselorId;
        this.createdAt = createdAt;
    }
}
