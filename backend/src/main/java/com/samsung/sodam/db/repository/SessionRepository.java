package com.samsung.sodam.db.repository;

import com.samsung.sodam.db.entity.ConsultSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<ConsultSession,Integer> {
}
