package com.samsung.sodam.db.entity;

import com.samsung.sodam.db.converter.CategoryConverter;
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

    @Column(length = 30)
    private String type;

    @Column(length = 100)
    private String title;

    @Column(length = 200)
    private String content;

    @Column
    private Integer views;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(mappedBy = "troubleBoard", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @OrderBy("id asc")
    private List<TroubleComment> troubleComment;
}
