package com.samsung.sodam.db.repository;

import com.samsung.sodam.db.entity.Counselor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class CounselorRepositoryImpl implements CounselorRepository {

    @PersistenceContext
    private EntityManager em;

    public Counselor findCounselor(String email) {
        Counselor counselor = em.find(Counselor.class, email);
        return counselor;
    }

    public Counselor saveCounselor(Counselor counselor) {
        em.persist(counselor);
        return counselor;
    }

    @Override
    public Optional<Counselor> findOne(Specification<Counselor> spec) {
        return Optional.empty();
    }

    @Override
    public List<Counselor> findAll(Specification<Counselor> spec) {
        List<Counselor> query = em.createQuery("select counselor from Counselor counselor", Counselor.class).getResultList();
        return query;
    }

    @Override
    public Page<Counselor> findAll(Specification<Counselor> spec, Pageable pageable) {
        return null;
    }

    @Override
    public List<Counselor> findAll(Specification<Counselor> spec, Sort sort) {
        return null;
    }

    @Override
    public long count(Specification<Counselor> spec) {
        return 0;
    }

    @Override
    public boolean exists(Specification<Counselor> spec) {
        return false;
    }
}
