package com.samsung.sodam.db.repository;

import com.samsung.sodam.db.entity.Counselor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CounselorRepository extends JpaRepository<Counselor,String> {

    // 아직 덜만들어서 주석처리
    //public void deleteByCounselorId(String id); 

    boolean existsByEmail(String email);

    Counselor getByCounselorId(String id);
    Counselor findByCounselorId(String id);


}
