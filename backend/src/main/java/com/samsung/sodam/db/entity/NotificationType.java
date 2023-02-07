package com.samsung.sodam.db.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum NotificationType {
    CONSULT(1, "상담"),
    TROUBLE(2, "고민댓글"),
    QNA(3, "문의댓글");

    private final Integer code;
    private final String value;

}
