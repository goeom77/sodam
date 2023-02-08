package com.samsung.sodam.db.entity;

import com.samsung.sodam.db.converter.CareerListConverter;
import com.samsung.sodam.db.converter.ConsultListConverter;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@DynamicInsert
public class Counselor extends Member {
    @Id
    @Column(length=30)
    private String id;

    @Column(length = 50)
    private String name;

    @Column(length = 100)
    private String password;

    @Column(length = 50)
    private String email;

    @Column(length = 50)
    private String tel;

    @Column(length = 300)
    @Convert(converter = CareerListConverter.class)
    private List<Career> career;
    //private String career;


    @Column(length = 150)
    private String introduce;

    @Column(length = 3)
    private String commonCodeId = "1";

    @Column(length = 3)
    private GENDER gender;

    @Column(columnDefinition = "boolean default false")
    private Boolean qualification;

    @Column(length = 28)
    private String routine;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "enterprise_id")
    private Enterprise enterprise;

    @Column(length = 200)
    private String profileImg;

    @Column(name = "consult_type")
    @Convert(converter = ConsultListConverter.class)
    private List<CONSULT_TYPE> consultTypeList = null;

    @OneToMany(mappedBy = "counselor")
//    @JoinColumn(name = "counselor_id" , referencedColumnName = "id")
    private List<Certificate> cert = new ArrayList<>();

    @OneToMany(mappedBy = "counselor")
    //@JoinColumn(name = "counselor_id" , referencedColumnName = "id")
    private List<Education> edu = new ArrayList<>();

    @Builder
    public Counselor(String id, String name, String password, String email, String tel, List<Career> career, String introduce, GENDER gender, Boolean qualification, String routine, Enterprise enterprise, String profileImg, List<CONSULT_TYPE> consultTypeList) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.tel = tel;
        this.career = career;
        this.introduce = introduce;
        this.gender = gender;
        this.qualification = qualification;
        this.routine = routine;
        this.enterprise = enterprise;
        this.profileImg = profileImg;
        this.consultTypeList = consultTypeList;
    }
}
