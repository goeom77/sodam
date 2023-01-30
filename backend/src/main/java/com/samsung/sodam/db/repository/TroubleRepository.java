package com.samsung.sodam.db.repository;

import com.samsung.sodam.api.response.TroubleOneResponse;
import com.samsung.sodam.db.entity.TroubleBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TroubleRepository extends JpaRepository<TroubleBoard, Long> {
}
