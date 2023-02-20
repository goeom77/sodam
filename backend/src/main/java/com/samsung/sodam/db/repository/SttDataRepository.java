package com.samsung.sodam.db.repository;

import com.samsung.sodam.db.entity.SttData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SttDataRepository extends JpaRepository<SttData,Long> {

    Optional<SttData> findByScheduleId(long id);
}
