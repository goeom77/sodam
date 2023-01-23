package com.samsung.sodam.db.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

@Repository
public class ClientCustomRepositoryImpl implements ClientCustomRepository{

    private final JPAQueryFactory queryFactory;

    public ClientCustomRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

//    @Modifying(clearAutomatically = true)
//    public void updateClientInfo(Client client) {
//        QClient qclient = QClient.client;
//
//        queryFactory.update(qclient)
//                .set(qclient.password, client.getPassword())
//                .where(qclient.clientId.eq(client.getClientId()))
//                .execute();
//    }
}
