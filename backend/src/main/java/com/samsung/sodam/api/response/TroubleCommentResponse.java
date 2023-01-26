package com.samsung.sodam.api.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TroubleCommentResponse {

    private int id;
    private String content;
    private String counselorId;
    private String counselorName;
    private String createdAt;
    private int likes;
    private boolean liked; // 좋아요 클릭여부
}
