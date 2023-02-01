package com.samsung.sodam.db.repository;

import com.samsung.sodam.api.request.FCMTokenRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FCMTokenRepository {

    private final StringRedisTemplate tokenRedisTemplate;

    public void saveToken(FCMTokenRequest fcmTokenRequest) {

    }

    public String getToken(String id) {
        return "";
    }

    public void deleteToken(String id) {

    }

    public boolean hasKey(String id) {
        return tokenRedisTemplate.hasKey(id);
    }
}
