package com.samsung.sodam.db.entity;

import com.querydsl.core.annotations.QueryProjection;
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

public class Review {
    @Id
    @Column(name = "review_id")
    @GeneratedValue
    Long id;

    @Column(length = 500)
    String contents;
    Double starts;
    @Column(name = "past_count")
    Integer pastCount;

    String type;
    @Column(name = "client_id")
    String clientId;

    @Column(name = "counselor_Id")
    String counselorId;

    @Column(name = "created_at")
    LocalDateTime createdAt;

    @QueryProjection
    public Review(Long id, String contents, Double starts, Integer pastCount, String type, String clientId, String counselorId, LocalDateTime createdAt) {
        this.id = id;
        this.contents = contents;
        this.starts = starts;
        this.pastCount = pastCount;
        this.type = type;
        this.clientId = clientId;
        this.counselorId = counselorId;
        this.createdAt = createdAt;
    }
}
