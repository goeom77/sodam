package com.samsung.sodam.db.repository;


import com.samsung.sodam.db.entity.Certificate;
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
public interface CertificateRepository extends JpaRepository<Certificate,String>{
    boolean existsById(long id);

    Certificate getById(long id);
    Optional<Certificate> findById(String id);

    void deleteById(long id);

    @Transactional
    @Query("select c.photo from Certificate c where c.id in :ids")
    List<ProfilePhoto> selectPhotoByIdInQuery(@Param("ids") List<Long> ids);

    @Transactional
    @Modifying
    @Query("delete from Certificate c where c.id in :ids")
    void deleteAllByIdInQuery(@Param("ids") List<Long> ids);
}
