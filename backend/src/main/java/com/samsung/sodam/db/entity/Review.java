package com.samsung.sodam.db.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;


@Table
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    @Id
    @Column(name = "review_id")
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
}
