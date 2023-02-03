package com.samsung.sodam.db.repository;

import com.samsung.sodam.db.entity.ConsultSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionRepository extends JpaRepository<ConsultSession,Integer> {

    public List<ConsultSession> findByCounselorId(String counselorId);
}
