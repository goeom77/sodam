package com.samsung.sodam.api.service;

import com.samsung.sodam.api.request.TroubleCommentRequest;
import com.samsung.sodam.api.request.TroubleRequest;
import com.samsung.sodam.api.response.TroubleOneResponse;
import com.samsung.sodam.api.response.TroubleResponse;
import com.samsung.sodam.db.entity.*;
import com.samsung.sodam.db.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TroubleServiceImpl implements TroubleService {
    private final NotificationService notificationService;

    private final TroubleRepository repository;
    private final TroubleCustomRepository customRepository;
    private final TroubleCommentRepository commentRepository;
    private final CounselorRepository counselorRepository;
    private final TroubleCommentLikeRepository commentLikeRepository;


    @Override
    public Page<TroubleResponse> getAllTroubleList(String searchWord, Pageable pageable) {
        return customRepository.getAllTroubleList(searchWord, pageable);
    }

    public Page<TroubleResponse> getTroubleList(String category, String searchWord, Pageable pageable) {
        return customRepository.getTroubleList(category, searchWord, pageable);
    }

    @Override
    public void saveTrouble(TroubleRequest request) {

        TroubleBoard troubleBoard = TroubleBoard.builder()
                .category(request.getCategory())
                .title(request.getTitle())
                .content(request.getContent())
                .clientId(request.getClientId())
                .build();

        repository.save(troubleBoard);
    }

    @Override
    public TroubleOneResponse getTrouble(String userId, Long id) {
        return customRepository.getTrouble(userId, id);
    }

    @Override
    public void updateTrouble(Long id, TroubleRequest request) {
        customRepository.updateTrouble(id, request);
    }

    @Override
    public void deleteTrouble(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Page<TroubleResponse> getMyTroubleList(String clientId, Pageable pageable) {

        return customRepository.getMyTroubleList(clientId, pageable);
    }

    @Override
    @Transactional
    @Modifying
    public void saveComment(TroubleCommentRequest request) {
        Optional<TroubleBoard> troubleBoard = repository.findById(request.getBoardId());
        Optional<Counselor> counselor = counselorRepository.findById(request.getCounselorId());

        TroubleComment troubleComment = TroubleComment.builder()
                .content(request.getContent())
                .troubleBoard(troubleBoard.orElseThrow(() -> new NoSuchElementException()))
                .counselor(counselor.orElseThrow(() -> new NoSuchElementException()))
                .build();

        commentRepository.save(troubleComment);

        notificationService.send(troubleBoard.get().getClientId(), NotificationType.TROUBLE,
                "고민게시글에 댓글이 등록되었습니다.", "", "/KidBoardDetail/" + troubleBoard.get().getId());
    }

    @Override
    public void updateComment(Long id, TroubleCommentRequest request) {
        customRepository.updateTroubleComment(id, request);
    }

    @Override
    public void deleteComment(Long id) {
        customRepository.deleteTroubleComment(id);
    }

    @Override
    public void commentLike(Long id, TroubleCommentRequest request) {
        boolean isLiked = customRepository.isTroubleCommentLike(id, request.getClientId());

        if(isLiked) {
            customRepository.unlikeTroubleComment(id);
        } else {

            TroubleComment troubleComment = customRepository.getTroubleComment(id);

            TroubleCommentLike troubleCommentLike = TroubleCommentLike.builder()
                    .troubleComment(troubleComment)
                    .clientId(request.getClientId())
                    .build();

            commentLikeRepository.save(troubleCommentLike);
        }
    }
}
