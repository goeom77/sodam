package com.samsung.sodam.db.repository;


import com.samsung.sodam.db.entity.Education;
import com.samsung.sodam.db.entity.ProfilePhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface EducationRepository extends JpaRepository<Education,String>{
    boolean existsById(long id);

    Education getById(long id);
    Optional<Education> findById(long id);

    void deleteById(long id);

    @Transactional
    @Query("select e.photo from Education e where e.id in :ids")
    List<ProfilePhoto> selectPhotoByIdInQuery(@Param("ids") List<Long> ids);

    @Transactional
    @Modifying
    @Query("delete from Education e where e.id in :ids")
    void deleteAllByIdInQuery(@Param("ids") List<Long> ids);
}
