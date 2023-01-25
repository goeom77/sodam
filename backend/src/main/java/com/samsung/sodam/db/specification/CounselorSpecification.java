package com.samsung.sodam.db.specification;

import com.samsung.sodam.db.entity.Counselor;
import org.springframework.data.jpa.domain.Specification;

public class CounselorSpecification {
    public static Specification<Counselor> equalFilterKeyword(String keyword){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), "%"+keyword+"%"));
    }
}
