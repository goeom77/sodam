package com.samsung.sodam.api.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.QueryProjection;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@ApiModel(description = "1:1문의 댓글 응답 모델")
@Getter
@Setter
@ToString
public class QnAAnswerResponse {

    private Long id;
    private String content;
    private String writerId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;

    @QueryProjection
    public QnAAnswerResponse(Long id, String content, String writerId, LocalDateTime createdAt) {
        this.id = id;
        this.content = content;
        this.writerId = writerId;
        this.createdAt = createdAt;
    }
}
