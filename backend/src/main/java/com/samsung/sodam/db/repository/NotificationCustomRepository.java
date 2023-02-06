package com.samsung.sodam.db.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.samsung.sodam.api.response.MyNotiResponse;
import com.samsung.sodam.api.response.MyOneNotiResponse;
import com.samsung.sodam.api.response.QMyNotiResponse;
import com.samsung.sodam.api.response.QMyOneNotiResponse;
import com.samsung.sodam.db.entity.NotificationType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

import static com.samsung.sodam.db.entity.QNotification.notification;

@Repository
@RequiredArgsConstructor
public class NotificationCustomRepository {

    private final JPAQueryFactory queryFactory;

    public Page<MyNotiResponse> getAllMyNoti(String userId, NotificationType type, Pageable pageable) {
        List<MyNotiResponse> list = queryFactory
                .select(new QMyNotiResponse(
                        notification.id,
                        notification.type,
                        notification.title,
                        notification.isRead,
                        notification.receiverId,
                        notification.createdAt
                ))
                .from(notification)
                .where(findType(type), notification.receiverId.eq(userId))
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .orderBy(notification.id.desc())
                .fetch();

        return new PageImpl<>(list, pageable, list.size());
    }
    private BooleanExpression findType(NotificationType type) {
        return type != null ? notification.type.eq(type) : null;
    }

    @Transactional
    public MyOneNotiResponse getOneMyNoti(Long id) {
        queryFactory
                .update(notification)
                .set(notification.isRead, true)
                .where(notification.id.eq(id))
                .execute();

        return queryFactory
                .select(new QMyOneNotiResponse(
                        notification.id,
                        notification.type,
                        notification.title,
                        notification.content,
                        notification.isRead,
                        notification.receiverId,
                        notification.createdAt
                ))
                .from(notification)
                .where(notification.id.eq(id))
                .fetchFirst();
    }
}
