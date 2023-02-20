package com.samsung.sodam.db.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.samsung.sodam.api.request.HelpDeskRequest;
import com.samsung.sodam.db.entity.Notice;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

import static com.samsung.sodam.db.entity.QNotice.notice;

@Repository
@RequiredArgsConstructor
public class NoticeCustomRepository {

    private final JPAQueryFactory queryFactory;


    public Page<Notice> getAllNoticeList(String searchWord, Pageable pageable) {

        // 상단 고정된 공지
        List<Notice> fixedNotices = queryFactory
                .selectFrom(notice)
                .where(notice.fixed.eq(true))
                .orderBy(notice.createdAt.desc())
                .fetch();

        // 일반 공지
        List<Notice> notices = queryFactory
                .selectFrom(notice)
                .where(notice.fixed.eq(false)
                        .and(containSearch(searchWord)))
                .orderBy(notice.createdAt.desc())
                .fetch();

        fixedNotices.addAll(notices);
        return new PageImpl<>(fixedNotices, pageable, fixedNotices.size());
    }
    private BooleanExpression containSearch(String searchWord) {
        return searchWord != null ? notice.title.contains(searchWord) : null;
    }

    @Transactional
    public Notice getOneNotice(Long id) {
        // 조회수 + 1
        queryFactory
                .update(notice)
                .set(notice.views, notice.views.add(1))
                .where(notice.id.eq(id))
                .execute();

        return queryFactory
                .selectFrom(notice)
                .where(notice.id.eq(id))
                .fetchFirst();
    }

    @Transactional
    public void updateNotice(Long id, HelpDeskRequest request) {
        queryFactory
                .update(notice)
                .set(notice.adminId, request.getWriterId())
                .set(notice.title, request.getTitle())
                .set(notice.content, request.getContent())
                .set(notice.fixed, request.isFixed())
                .where(notice.id.eq(id))
                .execute();
    }

}
