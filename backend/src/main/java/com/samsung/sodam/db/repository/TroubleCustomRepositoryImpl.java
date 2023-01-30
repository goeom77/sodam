package com.samsung.sodam.db.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.samsung.sodam.api.request.TroubleRequest;
import com.samsung.sodam.api.response.TroubleCommentResponse;
import com.samsung.sodam.api.response.TroubleOneResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

import static com.samsung.sodam.db.entity.QTroubleBoard.troubleBoard;
import static com.samsung.sodam.db.entity.QTroubleComment.troubleComment;

@Repository
@RequiredArgsConstructor
public class TroubleCustomRepositoryImpl implements TroubleCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<TroubleOneResponse> getTroubleList(String category, String searchWord, Pageable pageable) {

        List<TroubleOneResponse> list = queryFactory
                .select(Projections.bean(TroubleOneResponse.class,
                        troubleBoard.id,
                        troubleBoard.category,
                        troubleBoard.title,
                        troubleBoard.content,
                        troubleBoard.clientId,
                        troubleBoard.createdAt,
                        troubleBoard.updatedAt,
                        troubleBoard.views,
                        troubleBoard.comments
                        ))
                .from(troubleBoard)
                .where(eqCategory(category), containSearch(searchWord))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(troubleBoard.createdAt.desc())
                .fetch();

        return new PageImpl<>(list, pageable, list.size());
    }
    private BooleanExpression eqCategory(String category) {
        if(category == null || category.equals("")) return null;
        return troubleBoard.category.eq(category);
    }
    private BooleanExpression containSearch(String searchWord) {
        return searchWord != null ? troubleBoard.title.contains(searchWord) : null;
    }

    @Override
    @Transactional
    public TroubleOneResponse getTrouble(Long id) {
        // 조회수 + 1
        queryFactory.update(troubleBoard)
                .set(troubleBoard.views, troubleBoard.views.add(1))
                .where(troubleBoard.id.eq(id))
                .execute();

        // 게시글
        TroubleOneResponse result = queryFactory
                .select(Projections.bean(TroubleOneResponse.class,
                        troubleBoard.id,
                        troubleBoard.category,
                        troubleBoard.title,
                        troubleBoard.content,
                        troubleBoard.clientId,
                        troubleBoard.createdAt,
                        troubleBoard.updatedAt,
                        troubleBoard.views,
                        troubleBoard.comments
                        ))
                .from(troubleBoard)
                .where(troubleBoard.id.eq(id))
                .fetchFirst();

        // 댓글 리스트
        List<TroubleCommentResponse> comments = queryFactory
                .select(Projections.bean(TroubleCommentResponse.class,
                        troubleComment.id,
                        troubleComment.content,
                        troubleComment.counselor,
                        troubleComment.createdAt,
                        troubleComment.likes))
                .from(troubleComment)
                .where(troubleComment.troubleBoard.id.eq(id))
                .fetch();

        result.setCommentList(comments);

        return result;
    }

    @Override
    @Transactional
    @Modifying(clearAutomatically = true)
    public void updateTrouble(Long id, TroubleRequest request) {
        queryFactory
                .update(troubleBoard)
                .set(troubleBoard.category, request.getCategory())
                .set(troubleBoard.title, request.getTitle())
                .set(troubleBoard.content, request.getContent())
                .where(troubleBoard.id.eq(id))
                .execute();
    }

    @Override
    public Page<TroubleOneResponse> getMyTroubleList(String clientId, Pageable pageable) {
        List<TroubleOneResponse> list = queryFactory
                .select(Projections.bean(TroubleOneResponse.class,
                        troubleBoard.id,
                        troubleBoard.category,
                        troubleBoard.title,
                        troubleBoard.content,
                        troubleBoard.clientId,
                        troubleBoard.createdAt,
                        troubleBoard.updatedAt,
                        troubleBoard.views,
                        troubleBoard.comments
                ))
                .from(troubleBoard)
                .where(troubleBoard.clientId.eq(clientId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(troubleBoard.createdAt.desc())
                .fetch();
        return new PageImpl<>(list, pageable, list.size());
    }
}
