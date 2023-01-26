package com.samsung.sodam.db.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(FavoriteCounselorPK.class)
public class FavoriteCounselor {

    @Id
    @Column(name = "client_id")
    String clientId;
    @Id
    @Column(name = "counselor_id")
    String CounselorId;
}
