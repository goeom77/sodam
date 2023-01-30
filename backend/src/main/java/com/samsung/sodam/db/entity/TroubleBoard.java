package com.samsung.sodam.db.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.samsung.sodam.db.converter.CategoryConverter;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class TroubleBoard extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30)
    @Convert(converter = CategoryConverter.class)
    private String category;

    @Column(length = 100)
    private String title;

    @Column(length = 200)
    private String content;

    @Column
    private Integer views = 0;

    @Column
    private String clientId;

    @Column
    private Integer comments = 0;

    @OneToMany(mappedBy = "troubleBoard", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<TroubleComment> troubleComment;

    @Builder
    public TroubleBoard(String category, String title, String content, String clientId) {
        this.category = category;
        this.title = title;
        this.content = content;
        this.clientId = clientId;
        this.views = 0;
        this.comments = 0;
    }
}
