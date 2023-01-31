package com.samsung.sodam.api.request;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "고민게시글 댓글등록 요청 모델")
@Getter
@Setter
public class TroubleCommentRequest {

    private  Long id;
    private Long boardId;
    private String counselorId;
    private String clientId;
    private String content;

}