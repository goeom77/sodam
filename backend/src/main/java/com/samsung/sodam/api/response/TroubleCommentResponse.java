package com.samsung.sodam.api.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.QueryProjection;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@ApiModel(description = "고민게시글 댓글조회 응답 모델")
@Getter
@Setter
public class TroubleCommentResponse {

    @ApiModelProperty(value = "댓글 ID")
    private Long commentId;

    @ApiModelProperty(value = "해당 댓글 작성자(상담사)의 ID")
    private String counselorId;

    @ApiModelProperty(value = "댓글 내용")
    private String content;

    @ApiModelProperty(value = "해당 댓글의 생성일시")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;

    @ApiModelProperty(value = "해당 댓글 작성자(상담사)의 이름")
    private String counselorName;

    @ApiModelProperty(value = "해당 댓글 작성자(상담사)의 프로필 이미지 경로")
    private String counselorProfilePath;

    @ApiModelProperty(value = "해당 댓글의 좋아요 선택 여부 확인")
    private boolean liked;

    @ApiModelProperty(value = "해당 게시글의 작성자 본인 확인")
    private boolean myComment;

    @QueryProjection
    public TroubleCommentResponse(Long commentId, String counselorId, String content, LocalDateTime createdAt,
                                  String counselorName, String counselorProfilePath, boolean liked, boolean myComment) {
        this.commentId = commentId;
        this.counselorId = counselorId;
        this.content = content;
        this.createdAt = createdAt;
        this.counselorName = counselorName;
        this.counselorProfilePath = counselorProfilePath;
        this.liked = liked;
        this.myComment = myComment;
    }

}
