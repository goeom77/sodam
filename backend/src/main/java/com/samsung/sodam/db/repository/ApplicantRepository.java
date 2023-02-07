package com.samsung.sodam.db.repository;

import com.samsung.sodam.db.entity.ConsultApplicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicantRepository extends JpaRepository<ConsultApplicant, Integer> {

}
