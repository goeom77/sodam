package com.samsung.sodam.api.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.samsung.sodam.db.entity.Notification;
import com.samsung.sodam.db.entity.NotificationType;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@ApiModel(description = "알림 모델")
@Getter
@Setter
@ToString
public class NotificationResponse {

    private String receiverId;
    private NotificationType type;
    private String content;
    private String url;
    private boolean read;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;

    public String create(Notification notification) {
        String data = "";
        this.createdAt = notification.getCreatedAt();

        data += "'{\"receiverId\" : " + notification.getReceiverId() + "}'\n";
//        data += "\"type\" : " + notification.getType() + ",\n";
//        data += "\"content\" : " + notification.getContent() + ",\n";
//        data += "\"url\" : " + notification.getUrl() + ",\n";
//        data += "\"read\" : " + notification.isRead() + "\n}\n\n";

        return data;
    }

}
