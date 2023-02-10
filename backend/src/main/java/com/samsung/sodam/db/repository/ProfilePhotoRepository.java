package com.samsung.sodam.db.repository;

import com.samsung.sodam.db.entity.ProfilePhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProfilePhotoRepository extends JpaRepository<ProfilePhoto,String> {
    @Transactional
    @Modifying
    @Query("delete from ProfilePhoto p where p.id in :ids")
    void deleteAllByIdInQuery(@Param("ids") List<Long> ids);
}
