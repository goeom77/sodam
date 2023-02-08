package com.samsung.sodam.db.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 50)
    private String degree;  // 학위

    @Column(length = 50)
    private String school;  // 학교

    @Column(length = 100)
    private String major;  // 전공

    @Column(length = 30)
    private String is_graduate; // 학업 상태 (재학, 졸업, 수료)

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "photo", referencedColumnName = "id")
    private ProfilePhoto photo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "counselor_id", referencedColumnName = "id")
    private Counselor counselor;

}
