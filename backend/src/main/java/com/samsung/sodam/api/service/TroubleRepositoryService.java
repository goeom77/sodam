package com.samsung.sodam.api.service;

import com.samsung.sodam.api.request.TroubleCommentRequest;
import com.samsung.sodam.api.request.TroubleRequest;
import com.samsung.sodam.api.response.TroubleOneResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TroubleRepositoryService {

    Page<TroubleOneResponse> getTroubleList(String category, String searchWord, Pageable pageable);
    void saveTrouble(TroubleRequest request);
    TroubleOneResponse getTrouble(Long id);
    void updateTrouble(Long id, TroubleRequest request);
    void deleteTrouble(Long id);
    Page<TroubleOneResponse> getMyTroubleList(String clientId, Pageable pageable);

    void saveComment(TroubleCommentRequest request);
    void updateComment(Long id, TroubleCommentRequest request);
    void deleteComment(Long id);
    void commentLike(String clientId, Long id);
}
