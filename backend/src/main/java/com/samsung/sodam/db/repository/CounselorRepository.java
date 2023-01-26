package com.samsung.sodam.db.repository;

import com.samsung.sodam.api.request.CounselorSearchRequest;
import com.samsung.sodam.db.entity.Counselor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CounselorRepository extends JpaRepository<Counselor,String>{

    // Integer deleteByCounselorId(Integer id);

    // Counselor findCounselor(String email);

   // Counselor saveCounselor(Counselor counselor);

    boolean existsByEmail(String email);

    Counselor getById(String id);
    Optional<Counselor> findById(String id);

//    Page<Counselor> findAll(CounselorSearchRequest request, Pageable pageable);
}
