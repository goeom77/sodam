package com.samsung.sodam.db.repository;

import com.samsung.sodam.db.entity.ConsultSchedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScheduleRepository extends JpaRepository<ConsultSchedule, Integer> {

    Optional<ConsultSchedule> getConsultSchedulesBySessionId(
            Integer sessionId);

    Page<ConsultSchedule> getAllBySessionId(Integer sessionId, Pageable pageable);
}
