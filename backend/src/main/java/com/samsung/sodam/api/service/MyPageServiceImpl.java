package com.samsung.sodam.api.service;

import com.samsung.sodam.api.response.MyNotiResponse;
import com.samsung.sodam.api.response.MyOneNotiResponse;
import com.samsung.sodam.db.entity.NotificationType;
import com.samsung.sodam.db.repository.NotificationCustomRepository;
import com.samsung.sodam.db.repository.NotificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MyPageServiceImpl implements MyPageService {

    private final NotificationRepository notificationRepository;
    private final NotificationCustomRepository notificationCustomRepository;

    @Override
    public Page<MyNotiResponse> getAllMyNoti(String userId, NotificationType type, Pageable pageable) {
        return notificationCustomRepository.getAllMyNoti(userId, type, pageable);
    }

    @Override
    public MyOneNotiResponse getOneMyNoti(Long id) {
        return notificationCustomRepository.getOneMyNoti(id);
    }

    @Override
    public void deleteOneMyNoti(Long id) {
        notificationRepository.deleteById(id);
    }

}
