package com.samsung.sodam.api.service;

import com.samsung.sodam.api.response.NotificationResponse;
import com.samsung.sodam.db.repository.EmitterRepository;
import com.samsung.sodam.db.entity.Notification;
import com.samsung.sodam.db.entity.NotificationType;
import com.samsung.sodam.db.repository.NotificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;

@Service
@AllArgsConstructor
public class NotificationService {

    private final Long TIME_OUT = 24 * 60 * 60 * 10000L;
    private final EmitterRepository emitterRepository;
    private final NotificationRepository notificationRepository;

    public SseEmitter subscribe(String userId, String lastEventId) {
        String emitterId = makeTimeIncludeId(userId);
        SseEmitter emitter = emitterRepository.save(emitterId, new SseEmitter(TIME_OUT));
        emitter.onCompletion(() -> emitterRepository.deleteById(emitterId));
        emitter.onTimeout(() -> emitterRepository.deleteById(emitterId));

        // 503 에러를 방지하기 위한 더미 이벤트 전송
        String eventId = makeTimeIncludeId(userId);
        sendNotification(emitter, eventId, emitterId, "EventStream Created. [userId= " + userId + "]");

        // 클라이언트가 미수신한 event 목록이 존재할 경우 전송하여 event 유실을 예방
        if (hasLostData(lastEventId)) {
            sendLostData(lastEventId, userId, emitterId, emitter);
        }

        return emitter;
    }

    private String makeTimeIncludeId(String userId) {
        return userId + "_" + System.currentTimeMillis();
    }

    private void sendNotification(SseEmitter emitter, String eventId, String emitterId, Object data) {
        try {
            emitter.send(SseEmitter.event()
                    .id(eventId)
                    .data(data, MediaType.APPLICATION_JSON)
            );
        } catch (IOException e) {
            emitterRepository.deleteById(emitterId);
        }
    }

    private boolean hasLostData(String lastEventId) {
        return !lastEventId.isEmpty();
    }

    private void sendLostData(String lastEventId, String userId, String emitterId, SseEmitter emitter) {
        Map<String, Object> eventCaches = emitterRepository.findAllEventCacheStartWithByUserId(String.valueOf(userId));
        eventCaches.entrySet().stream()
                .filter(entry -> lastEventId.compareTo(entry.getKey()) < 0)
                .forEach(entry -> sendNotification(emitter, entry.getKey(), emitterId, entry.getValue()));
    }

    public void send(String userId, NotificationType notificationType, String title, String content, String url) {
        Notification notification = notificationRepository.save(createNotification(userId, notificationType, title, content, url));

        String eventId = userId + "_" + System.currentTimeMillis();
        Map<String, SseEmitter> emitters = emitterRepository.findAllEmitterStartWithByUserId(userId);

        emitters.forEach(
                (key, emitter) -> {
                    emitterRepository.saveEventCache(key, notification);
                    sendNotification(emitter, eventId, key, notification);
                }
        );
//        new NotificationResponse().create(notification)
    }

    private Notification createNotification(String userId, NotificationType notificationType, String title, String content, String url) {
        return Notification.builder()
                .receiverId(userId)
                .type(notificationType)
                .title(title)
                .content(content)
                .url(url)
                .isRead(false)
                .build();
    }
}
