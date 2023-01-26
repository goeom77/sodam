package com.samsung.sodam.db.specification;

import com.samsung.sodam.api.request.CounselorSearchRequest;
import com.samsung.sodam.db.entity.Counselor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class CounselorSpecification {
    public static Specification<Counselor> equalFilterKeyword(String keyword) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), "%" + keyword + "%"));
    }

    public static Specification<Counselor> nameLike(final String name) {
        return new Specification<Counselor>() {
            @Override
            public Predicate toPredicate(Root<Counselor> root,
                                         CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.like(root.get("name"), "%" + name + "%");
            }
        };
    }

    public static Specification<Counselor> majorLike(final String major) {
        return new Specification<Counselor>() {
            @Override
            public Predicate toPredicate(Root<Counselor> root,
                                         CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.like(root.get("tag"), "%" + major + "%");
            }
        };
    }

    public static Specification<Counselor> searchCounselor(CounselorSearchRequest searchRequest) {
        Specification<Counselor> spec = SpecBuilder.builder(Counselor.class)
                .and(CounselorSpecification.nameLike(searchRequest.getKeyword()))
                .and(CounselorSpecification.majorLike(searchRequest.getKeyword()))
                .toSpec();

        return spec;

    }
}
