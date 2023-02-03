package com.samsung.sodam.api.request;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ApiModel(description = "헬프데스크 글 등록 요청 모델")
@Getter
@Setter
@ToString
public class HelpDeskRequest {

    private Long id;
    private String title;
    private String content;
    private boolean fixed;
    private boolean secret;
    private String password;
    private Integer role;
    private String writerId;

}
