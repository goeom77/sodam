package com.samsung.sodam.db.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class QnABoard extends BaseTime{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String title;

    @Column(length = 200)
    private String content;

    @Column
    private boolean fixed;

    @Column
    private Integer role;

    @Column
    private String writerId;

    @Column
    private boolean secret;

    @Column
    private String password;

    @OneToMany(mappedBy = "qnABoard", orphanRemoval = true)
    private List<QnAAnswer> qnAAnswers = new ArrayList<>();


    @Builder
    public QnABoard(String title, String content, boolean fixed, Integer role, String writerId, boolean secret, String password) {
        this.title = title;
        this.content = content;
        this.fixed = fixed;
        this.role = role;
        this.writerId = writerId;
        this.secret = secret;
        this.password = password;
    }
}
