package com.samsung.sodam.api.service;

import com.samsung.sodam.api.request.TroubleCommentRequest;
import com.samsung.sodam.api.request.TroubleRequest;
import com.samsung.sodam.api.response.TroubleOneResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TroubleService {

    /**
     * 고민게시판 전체 목록 조회 (카테고리/검색)
     * @param userId
     * @param category
     * @param searchWord
     * @param pageable
     * @return
     */
    Page<TroubleOneResponse> getTroubleList(String userId, String category, String searchWord, Pageable pageable);

    /**
     * 고민게시글 작성
     * @param request clientId, category, title, content
     */
    void saveTrouble(TroubleRequest request);

    /**
     * 고민게시글 상세 조회
     * @param userId
     * @param id
     * @return
     */
    TroubleOneResponse getTrouble(String userId, Long id);

    /**
     * 고민게시글 수정
     * @param id
     * @param request
     */
    void updateTrouble(Long id, TroubleRequest request);

    /**
     * 고민게시글 삭제
     * @param id
     */
    void deleteTrouble(Long id);

    /**
     * 내가 쓴 고민게시글 목록 조회
     * @param clientId
     * @param pageable
     * @return
     */
    Page<TroubleOneResponse> getMyTroubleList(String clientId, Pageable pageable);

    /**
     * 고민게시글 댓글 등록
     * @param request id, boardId, content
     */
    void saveComment(TroubleCommentRequest request);

    /**
     * 고민게시글 댓글 수정
     * @param id
     * @param request
     */
    void updateComment(Long id, TroubleCommentRequest request);

    /**
     * 고민게시글 댓글 삭제
     * @param id
     */
    void deleteComment(Long id);

    /**
     * 고민게시글 댓글 좋아요 토글
     * @param id
     * @param request
     */
    void commentLike(Long id, TroubleCommentRequest request);
}
