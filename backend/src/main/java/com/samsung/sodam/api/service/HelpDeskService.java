package com.samsung.sodam.api.service;

import com.samsung.sodam.api.request.HelpDeskRequest;
import com.samsung.sodam.api.response.QnABoardResponse;
import com.samsung.sodam.api.response.QnAOneBoardResponse;
import com.samsung.sodam.db.entity.Notice;
import com.samsung.sodam.db.entity.QnABoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HelpDeskService {

    Page<Notice> getAllNoticeList(String searchWord, Pageable pageable);
    Notice getOneNotice(Long id);
    Page<QnABoardResponse> getAllQnaBoard(String userId, String searchWord, Pageable pageable);
    Page<QnABoardResponse> getMyQnaBoard(String userId, Pageable pageable);
    QnAOneBoardResponse getOneQnaBoard(String userId, Long id);
    void saveQnaBoard(HelpDeskRequest request);
    void updateQnaBoard(Long id, HelpDeskRequest request);
    void deleteQnaBoard(Long id);

}
