package com.samsung.sodam.api.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.QueryProjection;
import com.samsung.sodam.db.entity.QnAAnswer;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@ApiModel(description = "1:1문의 상세조회 응답 모델")
@Getter
@Setter
@ToString
public class QnABoardResponse {

    private Long id;
    private String title;
    private String writerId;
    private boolean fixed;
    private boolean secret;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;
    private Integer commentCount;
    private boolean myPost;
    private List<QnAAnswer> comments;

    @QueryProjection
    public QnABoardResponse(Long id, String title, String writerId, boolean fixed, boolean secret,
                            LocalDateTime createdAt, Integer commentCount, boolean myPost) {
        this.id = id;
        this.title = title;
        this.writerId = writerId;
        this.fixed = fixed;
        this.secret = secret;
        this.createdAt = createdAt;
        this.commentCount = commentCount;
        this.myPost = myPost;
    }
}
