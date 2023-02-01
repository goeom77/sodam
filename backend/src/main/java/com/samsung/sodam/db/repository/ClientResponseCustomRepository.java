package com.samsung.sodam.db.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.samsung.sodam.api.response.ClientListResponse;
import com.samsung.sodam.api.response.ClientListResponse.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ClientResponseCustomRepository {
//    @PersistenceContext
//    private final EntityManager entityManager;
//    private final JPAQueryFactory queryFactory;
//
//
//    public Page<ClientListResponse> getAllClientList(String counselorId, Pageable pageable){
//        List<ClientListResponse> list = queryFactory.select(Projections.bean(ClientListResponse.class, .name, player.age))
//
////        List<ClientListResponse> list = null;
//        return new PageImpl<>(list, pageable, list.size());
//    }
}
