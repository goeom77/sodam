package com.samsung.sodam.db.repository;

import com.samsung.sodam.db.entity.TroubleBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface TroubleCustomRepository {


    Page<TroubleBoard> getTroubleBoardList(String category, String searchWord, Pageable pageable);

}
