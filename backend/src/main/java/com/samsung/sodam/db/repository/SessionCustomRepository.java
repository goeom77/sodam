package com.samsung.sodam.db.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.samsung.sodam.api.request.RoomRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.samsung.sodam.db.entity.QConsultSession.consultSession;

@Repository
@RequiredArgsConstructor
public class SessionCustomRepository {

    private final JPAQueryFactory queryFactory;

    public void createSession(RoomRequest request) {  // openvidu 화상세션 id 추가
        queryFactory
                .update(consultSession)
                .set(consultSession.openviduId, request.getOpenviduId())
                .where(consultSession.id.eq(request.getSessionId()))
                .execute();
    }

    public void deleteSession(RoomRequest request) {  // openvidu 화상세션 id 삭제
        queryFactory
                .update(consultSession)
                .set(consultSession.openviduId, "")
                .where(consultSession.id.eq(request.getSessionId()))
                .execute();
    }

}
