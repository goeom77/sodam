package com.samsung.sodam.api.response;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TroubleDetailResponse {

    private int boardId;
    private String category;
    private String type;
    private String title;
    private String content;
    private String clientId;
    private String clientName;
    private String createdAt;
    private int views;
    private int commentCnt;

    private List<TroubleCommentResponse> comments = new ArrayList<>();


}
