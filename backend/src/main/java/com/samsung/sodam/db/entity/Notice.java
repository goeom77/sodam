package com.samsung.sodam.db.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Getter
@Setter
@DynamicUpdate
@NoArgsConstructor
public class Notice extends BaseTime {

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
    private Integer views;

    @Column
    private String adminId;

    @Builder
    public Notice(String title, String content, boolean fixed, String adminId) {
        this.title = title;
        this.content = content;
        this.fixed = fixed;
        this.views = 0;
        this.adminId = adminId;
    }

}
