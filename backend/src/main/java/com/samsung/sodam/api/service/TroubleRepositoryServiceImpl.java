package com.samsung.sodam.api.service;

import com.samsung.sodam.api.request.TroubleCommentRequest;
import com.samsung.sodam.api.request.TroubleRequest;
import com.samsung.sodam.api.response.TroubleOneResponse;
import com.samsung.sodam.db.entity.TroubleBoard;
import com.samsung.sodam.db.repository.ClientRepository;
import com.samsung.sodam.db.repository.TroubleCustomRepository;
import com.samsung.sodam.db.repository.TroubleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TroubleRepositoryServiceImpl implements TroubleRepositoryService {

    private final TroubleRepository repository;
    private final TroubleCustomRepository customRepository;
    private final ClientRepository clientRepository;


    public Page<TroubleOneResponse> getTroubleList(String category, String searchWord, Pageable pageable) {
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
    public TroubleOneResponse getTrouble(Long id) {
        return customRepository.getTrouble(id);
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
    public Page<TroubleOneResponse> getMyTroubleList(String clientId, Pageable pageable) {

        return customRepository.getMyTroubleList(clientId, pageable);
    }

    @Override
    public void saveComment(TroubleCommentRequest request) {

    }

    @Override
    public void updateComment(Long id, TroubleCommentRequest request) {

    }

    @Override
    public void deleteComment(Long id) {

    }

    @Override
    public void commentLike(String clientId, Long id) {

    }
}
