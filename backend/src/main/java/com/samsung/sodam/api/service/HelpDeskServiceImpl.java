package com.samsung.sodam.api.service;

import com.samsung.sodam.api.request.HelpDeskRequest;
import com.samsung.sodam.api.response.QnABoardResponse;
import com.samsung.sodam.api.response.QnAOneBoardResponse;
import com.samsung.sodam.db.entity.Notice;
import com.samsung.sodam.db.entity.QnABoard;
import com.samsung.sodam.db.repository.NoticeCustomRepository;
import com.samsung.sodam.db.repository.QnACustomRepository;
import com.samsung.sodam.db.repository.QnARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HelpDeskServiceImpl implements HelpDeskService {

    private final NoticeCustomRepository noticeCustomRepository;
    private final QnARepository qnARepository;
    private final QnACustomRepository qnACustomRepository;

    @Override
    public Page<Notice> getAllNoticeList(String searchWord, Pageable pageable) {
        return noticeCustomRepository.getAllNoticeList(searchWord, pageable);
    }

    @Override
    public Notice getOneNotice(Long id) {
        return noticeCustomRepository.getOneNotice(id);
    }

    @Override
    public Page<QnABoardResponse> getAllQnaBoard(String userId, String searchWord, Pageable pageable) {
        return qnACustomRepository.getAllQnaBoard(userId, searchWord, pageable);
    }

    @Override
    public Page<QnABoardResponse> getMyQnaBoard(String userId, Pageable pageable) {
        return qnACustomRepository.getMyQnaBoard(userId, pageable);
    }

    @Override
    public QnAOneBoardResponse getOneQnaBoard(String userId, Long id) {
        return qnACustomRepository.getOneQnaBoard(userId, id);
    }

    @Override
    public void saveQnaBoard(HelpDeskRequest request) {
        QnABoard qnABoard = QnABoard.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .fixed(request.isFixed())
                .secret(request.isSecret())
                .password(request.getPassword())
                .role(request.getRole())
                .writerId(request.getWriterId())
                .build();

        qnARepository.save(qnABoard);
    }

    @Override
    public void updateQnaBoard(Long id, HelpDeskRequest request) {
        qnACustomRepository.updateQnaBoard(id, request);
    }

    @Override
    public void deleteQnaBoard(Long id) {
        qnARepository.deleteById(id);
    }
}
