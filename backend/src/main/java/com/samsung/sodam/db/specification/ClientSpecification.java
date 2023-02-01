package com.samsung.sodam.db.specification;

import com.samsung.sodam.api.request.CounselorSearchRequest;
import com.samsung.sodam.db.entity.Client;
import com.samsung.sodam.db.entity.ConsultSession;
import com.samsung.sodam.db.entity.Counselor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.List;

public class ClientSpecification {
    public static Specification<Client> byCounselorId(String id) {

        return (root, query, criteriaBuilder) -> {
            Root<Client> client = query.from(Client.class);
            Root<ConsultSession> sessions = query.from(ConsultSession.class);

            Predicate joinPredicate = criteriaBuilder.equal(client.get("id"),sessions.get("clientId"));

            query.multiselect(
                    client.get("name"),
                    client.get("tel"),
                    client.get("email")
            ).where(
                    joinPredicate

            );

            criteriaBuilder.equal(client.get("id"), id);
            return joinPredicate;
        };
    }

    public static Specification<Counselor> counselorIdLike(final String name) {
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

//    public static Specification<Counselor> searchCounselor(CounselorSearchRequest searchRequest) {
//        Specification<Counselor> spec = SpecBuilder.builder(Counselor.class)
//                .and(ClientSpecification.nameLike(searchRequest.getKeyword()))
//                .and(ClientSpecification.majorLike(searchRequest.getKeyword()))
//                .toSpec();
//
//        return spec;
//
//    }
}
