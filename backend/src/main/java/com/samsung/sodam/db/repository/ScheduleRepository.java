package com.samsung.sodam.db.repository;

import com.samsung.sodam.api.request.CounselorSearchRequest;
import com.samsung.sodam.db.entity.ConsultSchedule;
import com.samsung.sodam.db.entity.Counselor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScheduleRepository extends JpaRepository<ConsultSchedule, Integer> {

    Optional<ConsultSchedule> getConsultSchedulesBySessionId(String sessionId);

    Page<ConsultSchedule> getAllBySessionId(Integer sessionId, Pageable pageable);
}
