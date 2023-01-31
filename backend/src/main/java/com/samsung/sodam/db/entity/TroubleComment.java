package com.samsung.sodam.db.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class TroubleComment extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private TroubleBoard troubleBoard;

    @OneToMany(mappedBy = "troubleComment", orphanRemoval = true)
    private List<TroubleCommentLike> likes = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "counselor_id")
    private Counselor counselor;

    @Builder
    public TroubleComment(String content, TroubleBoard troubleBoard, Counselor counselor) {
        this.content = content;
        this.troubleBoard = troubleBoard;
        this.counselor = counselor;
    }

}
