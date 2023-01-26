package com.samsung.sodam.api.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TroubleCommentRequest {

    private  int id;
    private int boardId;
    private String counselorId;
    private String counselorName;
    private String content;

}
