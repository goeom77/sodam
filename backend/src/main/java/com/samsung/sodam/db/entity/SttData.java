package com.samsung.sodam.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class SttData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(length = 200)
    private String url;

    @Column(length = 100)
    private String gcs_directory;

    @Column(length = 100)
    private String file_name;

    private long session_id;
}
