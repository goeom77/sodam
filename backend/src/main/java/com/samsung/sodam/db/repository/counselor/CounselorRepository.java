package com.samsung.sodam.db.repository.counselor;

import com.samsung.sodam.api.response.CounselorListResponse;
import com.samsung.sodam.db.entity.CONSULT_TYPE;
import com.samsung.sodam.db.entity.Counselor;
import com.samsung.sodam.db.entity.GENDER;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CounselorRepository extends JpaRepository<Counselor,String>{
    // Integer deleteByCounselorId(Integer id);

    // Counselor findCounselor(String email);

   // Counselor saveCounselor(Counselor counselor);

    boolean existsByEmail(String email);

    Counselor getById(String id);
    Optional<Counselor> findById(String id);
    Optional<Counselor> findByEmail(String email);

    Counselor getByEmail(String email);

//    Page<Counselor> findAll(CounselorSearchRequest request, Pageable pageable);

    void deleteById(String id);

    List<Counselor> findAllByNameLikeAndGenderEquals(String name, GENDER gender);

//    List<CounselorListResponse> findAllByConsultTypeListContaining()
}
