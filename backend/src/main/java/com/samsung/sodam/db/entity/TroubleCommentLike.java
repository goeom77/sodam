package com.samsung.sodam.db.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class TroubleCommentLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private TroubleComment troubleComment;

    @Column(name = "client_id")
    private String clientId;

    @Builder
    public TroubleCommentLike(TroubleComment troubleComment, String clientId) {
        this.troubleComment = troubleComment;
        this.clientId = clientId;
    }

}
