package com.samsung.sodam.api.service;

import com.samsung.sodam.api.response.MyNotiResponse;
import com.samsung.sodam.api.response.MyOneNotiResponse;
import com.samsung.sodam.db.entity.NotificationType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface MyPageService {

    Page<MyNotiResponse> getAllMyNoti(String userId, NotificationType type, Pageable pageable);
    MyOneNotiResponse getOneMyNoti(Long id);
    void deleteOneMyNoti(Long id);
    Integer unreadNotiCount(String userId);
    void uploadProfileImg(MultipartFile file, String id) throws IOException;

}
