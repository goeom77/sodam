package com.samsung.sodam.db.repository;

import com.samsung.sodam.db.entity.Counselor;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

public interface CounselorRepository extends JpaSpecificationExecutor<Counselor> {
    Counselor findCounselor(String email);

    Counselor saveCounselor(Counselor counselor);

}
