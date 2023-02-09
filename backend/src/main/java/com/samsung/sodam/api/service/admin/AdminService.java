package com.samsung.sodam.api.service.admin;

import com.samsung.sodam.api.request.HelpDeskRequest;
import com.samsung.sodam.db.entity.Notice;

public interface AdminService {

    void saveNotice(HelpDeskRequest request);
    Notice getOneNotice(Long id);
    void updateNotice(Long id, HelpDeskRequest request);
    void deleteNotice(Long id);
    void saveQnaAnswer(HelpDeskRequest request);
    void updateQnaAnswer(Long id, HelpDeskRequest request);
    void deleteQnaAnswer(Long id);
}
