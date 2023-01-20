package com.samsung.sodam.db.repository;

import com.samsung.sodam.db.entity.Counselor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CounselorRepository extends JpaRepository<Counselor,Integer> {

    Integer deleteByCounselorId(Integer id);
}
