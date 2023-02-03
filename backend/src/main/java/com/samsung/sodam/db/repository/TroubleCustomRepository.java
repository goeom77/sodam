package com.samsung.sodam.db.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.samsung.sodam.api.request.TroubleCommentRequest;
import com.samsung.sodam.api.request.TroubleRequest;
import com.samsung.sodam.api.response.*;
import com.samsung.sodam.db.entity.TroubleComment;
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
import static com.samsung.sodam.db.entity.QTroubleCommentLike.troubleCommentLike;
import static com.samsung.sodam.db.entity.QCounselor.counselor;

@Repository
@RequiredArgsConstructor
public class TroubleCustomRepository {

    private final JPAQueryFactory queryFactory;

    public Page<TroubleResponse> getAllTroubleList(String searchWord, Pageable pageable) {

        List<TroubleResponse> list = queryFactory
                .select(new QTroubleResponse(
                        troubleBoard.id,
                        troubleBoard.category,
                        troubleBoard.title,
                        troubleBoard.content,
                        troubleBoard.clientId,
                        troubleBoard.views,
                        troubleBoard.createdAt,
                        troubleBoard.comments.size()
                ))
                .from(troubleBoard)
                .where(containSearch(searchWord))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(troubleBoard.createdAt.desc())
                .fetch();

        return new PageImpl<>(list, pageable, list.size());
    }

    public Page<TroubleResponse> getTroubleList(String category, String searchWord, Pageable pageable) {

        List<TroubleResponse> list = queryFactory
                .select(new QTroubleResponse(
                        troubleBoard.id,
                        troubleBoard.category,
                        troubleBoard.title,
                        troubleBoard.content,
                        troubleBoard.clientId,
                        troubleBoard.views,
                        troubleBoard.createdAt,
                        troubleBoard.comments.size()
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

    @Transactional
    public TroubleOneResponse getTrouble(String userId, Long id) {
        // 조회수 + 1
        queryFactory.update(troubleBoard)
                .set(troubleBoard.views, troubleBoard.views.add(1))
                .where(troubleBoard.id.eq(id))
                .execute();

        // 게시글
        TroubleOneResponse result = queryFactory
                .select(new QTroubleOneResponse(
                        troubleBoard.id,
                        troubleBoard.category,
                        troubleBoard.title,
                        troubleBoard.content,
                        troubleBoard.clientId,
                        troubleBoard.views,
                        troubleBoard.createdAt,
                        troubleBoard.comments.size(),
                        JPAExpressions
                                .selectFrom(troubleBoard)
                                .where(troubleBoard.clientId.eq(userId))
                                .exists()
                ))
                .from(troubleBoard)
                .where(troubleBoard.id.eq(id))
                .fetchFirst();

        // 댓글 리스트
        List<TroubleCommentResponse> comments = queryFactory
                .select(new QTroubleCommentResponse(
                        troubleComment.id,
                        troubleComment.counselor.id,
                        troubleComment.content,
                        troubleComment.createdAt,
                        counselor.name,
                        counselor.profileImg,
                        JPAExpressions
                                .selectFrom(troubleCommentLike)
                                .where(troubleCommentLike.clientId.eq(userId))
                                .exists(),
                        JPAExpressions
                                .selectFrom(troubleComment)
                                .where(troubleComment.counselor.id.eq(userId))
                                .exists()
                ))
                .from(troubleComment)
                .innerJoin(troubleComment.counselor, counselor)
                .where(troubleComment.troubleBoard.id.eq(id))
                .fetch();

        result.setCommentList(comments);

        return result;
    }

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

    public Page<TroubleResponse> getMyTroubleList(String clientId, Pageable pageable) {
        List<TroubleResponse> list = queryFactory
                .select(new QTroubleResponse(
                        troubleBoard.id,
                        troubleBoard.category,
                        troubleBoard.title,
                        troubleBoard.content,
                        troubleBoard.clientId,
                        troubleBoard.views,
                        troubleBoard.createdAt,
                        troubleBoard.comments.size()
                ))
                .from(troubleBoard)
                .where(troubleBoard.clientId.eq(clientId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(troubleBoard.createdAt.desc())
                .fetch();
        return new PageImpl<>(list, pageable, list.size());
    }

    @Transactional
    public void updateTroubleComment(Long id, TroubleCommentRequest request) {
        queryFactory
                .update(troubleComment)
                .set(troubleComment.content, request.getContent())
                .where(troubleComment.id.eq(id))
                .execute();
    }

    @Transactional
    public void deleteTroubleComment(Long id) {
        // 좋아요 삭제
        queryFactory
                .delete(troubleCommentLike)
                .where(troubleCommentLike.troubleComment.id.eq(id))
                .execute();
        // 댓글 삭제
        queryFactory
                .delete(troubleComment)
                .where(troubleComment.id.eq(id))
                .execute();

    }

    @Transactional
    public void unlikeTroubleComment(Long id) {
        queryFactory
                .delete(troubleCommentLike)
                .where(troubleCommentLike.troubleComment.id.eq(id))
                .execute();
    }

    public boolean isTroubleCommentLike(Long id, String clientId) {
        return queryFactory
                .selectFrom(troubleCommentLike)
                .where(troubleCommentLike.troubleComment.id.eq(id)
                        .and(troubleCommentLike.clientId.eq(clientId)))
                .fetchFirst() != null;
    }

    public TroubleComment getTroubleComment(Long id) {
        return queryFactory
                .selectFrom(troubleComment)
                .where(troubleComment.id.eq(id))
                .fetchFirst();
    }
}
