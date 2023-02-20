package com.samsung.sodam.db.repository;

import com.samsung.sodam.db.entity.QnAAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QnAAnswerRepository extends JpaRepository<QnAAnswer, Long> {
}
