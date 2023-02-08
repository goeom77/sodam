package com.samsung.sodam.db.repository;


import com.samsung.sodam.db.entity.ProfilePhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface profilePhotoRepository extends JpaRepository<ProfilePhoto,String>{
//    boolean existsById(long id);
//
//    Certificate getById(long id);
//    Optional<CertificatePhoto> findById(String id);
//
//    void deleteById(long id);
}
