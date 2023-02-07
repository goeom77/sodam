package com.samsung.sodam.db.repository;


import com.samsung.sodam.db.entity.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EducationRepository extends JpaRepository<Education,String>{
    boolean existsById(long id);

    Education getById(long id);
    Optional<Education> findById(long id);

    void deleteById(long id);
}
