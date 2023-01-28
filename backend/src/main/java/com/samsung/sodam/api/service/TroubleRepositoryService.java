package com.samsung.sodam.api.service;

import com.samsung.sodam.db.entity.TroubleBoard;
import com.samsung.sodam.db.repository.TroubleCustomRepositoryImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TroubleRepositoryService {

    TroubleCustomRepositoryImpl repository;

    public Page<TroubleBoard> getTroubleList(String category, String searchWord, Pageable pageable) {
        return repository.getTroubleBoardList(category, searchWord, pageable);
    }
}
