package com.samsung.sodam.db.repository;

import com.samsung.sodam.db.entity.ConsultApplicant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicantRepository extends JpaRepository<ConsultApplicant,Integer> {
}
