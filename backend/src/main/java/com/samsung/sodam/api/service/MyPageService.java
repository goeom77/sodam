package com.samsung.sodam.api.service;

import com.samsung.sodam.api.response.MyNotiResponse;
import com.samsung.sodam.api.response.MyOneNotiResponse;
import com.samsung.sodam.db.entity.NotificationType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MyPageService {

    Page<MyNotiResponse> getAllMyNoti(String userId, NotificationType type, Pageable pageable);
    MyOneNotiResponse getOneMyNoti(Long id);
    void deleteOneMyNoti(Long id);

}
