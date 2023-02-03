package com.samsung.sodam.db.entity;

import com.samsung.sodam.db.converter.CategoryConverter;
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
    private Integer views;

    @Column
    private String clientId;

    @OneToMany(mappedBy = "troubleBoard", orphanRemoval = true)
    private List<TroubleComment> comments = new ArrayList<>();

    @Builder
    public TroubleBoard(String category, String title, String content, String clientId) {
        this.category = category;
        this.title = title;
        this.content = content;
        this.clientId = clientId;
        this.views = 0;
    }
}
