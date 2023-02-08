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
public class Certificate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 30)
    private String name;  // 자격증 이름

    @Column(length = 30)
    private String serial_num;  // 일련번호

    @Column(length = 30)
    private String agency;  // 기업명

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "photo", referencedColumnName = "id")
    private ProfilePhoto photo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "counselor_id", referencedColumnName = "id")
    private Counselor counselor;

}
