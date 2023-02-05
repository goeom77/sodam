package com.samsung.sodam.db.repository;

import com.samsung.sodam.api.response.CounselorListResponse;
import com.samsung.sodam.db.entity.Counselor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CounselorCustomRepository{
    CounselorListResponse getCounselorDetail(String id);
}
