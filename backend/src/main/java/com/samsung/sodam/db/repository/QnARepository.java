package com.samsung.sodam.db.repository;

import com.samsung.sodam.db.entity.QnABoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QnARepository extends JpaRepository<QnABoard, Long> {
}
