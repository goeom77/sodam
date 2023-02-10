package com.samsung.sodam.api.service;

import com.samsung.sodam.api.response.MyNotiResponse;
import com.samsung.sodam.api.response.MyOneNotiResponse;
import com.samsung.sodam.db.entity.Client;
import com.samsung.sodam.db.entity.Counselor;
import com.samsung.sodam.db.entity.Member;
import com.samsung.sodam.db.entity.NotificationType;
import com.samsung.sodam.db.repository.ClientRepository;
import com.samsung.sodam.db.repository.counselor.CounselorRepository;
import com.samsung.sodam.db.repository.NotificationCustomRepository;
import com.samsung.sodam.db.repository.NotificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@AllArgsConstructor
public class MyPageServiceImpl implements MyPageService {

    private final NotificationRepository notificationRepository;
    private final NotificationCustomRepository notificationCustomRepository;
    private final ClientRepository clientRepository;
    private final CounselorRepository counselorRepository;

    private final FileGCSService fileService;
    private final AuthService authService;


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

    @Override
    public Integer unreadNotiCount(String userId) {
        return notificationCustomRepository.unreadNotiCount(userId);
    }

    @Override
    public void uploadProfileImg(MultipartFile file, String id) throws IOException {
        String url = fileService.fileUploadGCS(file, "profile");
        Member m = authService.getMemberById(id);
//        Member m = authService.getMemberByEmail(id);
        System.out.println(m);

        m.setProfileImg(url);

        if (m instanceof Counselor) {    // Counselor일때 Member로 들어감
            Counselor c = (Counselor) m;
            counselorRepository.save(c);
        } else if (m instanceof Client) {
            Client c = (Client) m;
            clientRepository.save(c);
        }
    }

}
