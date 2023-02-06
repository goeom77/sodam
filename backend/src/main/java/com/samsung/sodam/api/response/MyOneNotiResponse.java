package com.samsung.sodam.api.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.QueryProjection;
import com.samsung.sodam.db.entity.NotificationType;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@ApiModel(description = "내게 온 알림 응답 모델")
@Getter
@Setter
@ToString
public class MyOneNotiResponse {

    private Long id;
    private NotificationType type;
    private String title;
    private String content;
    private boolean isRead;
    private String receiverId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;

    @QueryProjection
    public MyOneNotiResponse(Long id, NotificationType type, String title, String content, boolean isRead, String receiverId, LocalDateTime createdAt) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.content = content;
        this.isRead = isRead;
        this.receiverId = receiverId;
        this.createdAt = createdAt;
    }

}
