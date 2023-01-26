package com.samsung.sodam.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class TroubleCommentLike implements Serializable {

    @Id
    @ManyToOne
    private TroubleComment troubleComment;

    @Id
    @Column(name = "client_id")
    private String clientId;
}
