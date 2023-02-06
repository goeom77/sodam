package com.samsung.sodam.db.entity;

import com.samsung.sodam.db.converter.CategoryConverter;
import com.samsung.sodam.db.converter.NotiTypeConverter;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Notification extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Convert(converter = NotiTypeConverter.class)
    private NotificationType type;

    @Column
    private String url;

    @Column
    private boolean isRead;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private String receiverId;

    @Builder
    public Notification(String receiverId, NotificationType type, String title, String content, String url, boolean isRead) {
        this.receiverId = receiverId;
        this.type = type;
        this.title = title;
        this.content = content;
        this.url = url;
        this.isRead = isRead;
    }

}
