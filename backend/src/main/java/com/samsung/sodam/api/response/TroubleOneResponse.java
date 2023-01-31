package com.samsung.sodam.api.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.QueryProjection;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@ApiModel(description = "고민게시글 상세조회 응답 모델")
@Getter
@Setter
@ToString
public class TroubleOneResponse {

    @ApiModelProperty(value = "게시글 ID")
    private Long postId;

    @ApiModelProperty(value = "게시글 카테고리")
    private String category;

    @ApiModelProperty(value = "게시글 제목")
    private String title;

    @ApiModelProperty(value = "게시글 내용")
    private String content;

    @ApiModelProperty(value = "작성자(내담자)의 ID")
    private String clientId;

    @ApiModelProperty(value = "해당 게시글의 조회수")
    private Integer views;

    @ApiModelProperty(value = "해당 게시글의 생성일시")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;

    @ApiModelProperty(value = "해당 게시글의 댓글수")
    private Integer commentCount;

    @ApiModelProperty(value = "해당 게시글의 작성자 본인 확인")
    private boolean myPost;

    @ApiModelProperty(value = "해당 게시글의 댓글 목록")
    private List<TroubleCommentResponse> commentList;

    @QueryProjection
    public TroubleOneResponse(Long postId, String category, String title, String content, String clientId,
                              Integer views, LocalDateTime createdAt, Integer commentCount, boolean myPost) {
        this.postId = postId;
        this.category = category;
        this.title = title;
        this.content = content;
        this.clientId = clientId;
        this.views = views;
        this.createdAt = createdAt;
        this.commentCount = commentCount;
        this.myPost = myPost;
    }

}
