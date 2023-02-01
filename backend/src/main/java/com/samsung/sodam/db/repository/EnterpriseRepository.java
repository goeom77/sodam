package com.samsung.sodam.db.repository;

import com.samsung.sodam.db.entity.Enterprise;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnterpriseRepository extends JpaRepository<Enterprise, Integer> {
    @Query("SELECT new Enterprise (enterpriseId, enterprise, managerId, corporateCode) from Enterprise where enterprise like %:searchWord% ")
    List<Enterprise> searchByEnterpriseName(@Param("searchWord") String searchWord, Pageable pageable);

    Boolean existsByEnterpriseId(int enterpriseId);

}
