package com.samsung.sodam.db.repository;


import com.samsung.sodam.db.entity.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate,String>{
    boolean existsById(long id);

    Certificate getById(long id);
    Optional<Certificate> findById(String id);

    void deleteById(long id);
}
