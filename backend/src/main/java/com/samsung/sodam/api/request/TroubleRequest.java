package com.samsung.sodam.api.request;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ApiModel(description = "고민게시글 등록 요청 모델")
@Getter
@Setter
@ToString
public class TroubleRequest {

    private String clientId;
    private String category;
    private String title;
    private String content;

}
