package com.samsung.sodam.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class TroubleComment extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200)
    private String content;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private TroubleBoard troubleBoard;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_like")
    private List<TroubleCommentLike> troubleCommentLike;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "counselor_id")
    private Counselor counselor;
}
