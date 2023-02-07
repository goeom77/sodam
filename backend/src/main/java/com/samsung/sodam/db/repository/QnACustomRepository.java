package com.samsung.sodam.db.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.samsung.sodam.api.request.HelpDeskRequest;
import com.samsung.sodam.api.response.*;
import com.samsung.sodam.db.entity.QnAAnswer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import static com.samsung.sodam.db.entity.QQnABoard.qnABoard;
import static com.samsung.sodam.db.entity.QQnAAnswer.qnAAnswer;

@Repository
@RequiredArgsConstructor
public class QnACustomRepository {

    private final JPAQueryFactory queryFactory;

    public Page<QnABoardResponse> getAllQnaBoard(String userId, String searchWord, Pageable pageable) {
        List<QnABoardResponse> fixedQna = queryFactory
                .select(new QQnABoardResponse(
                        qnABoard.id,
                        qnABoard.title,
                        qnABoard.writerId,
                        qnABoard.fixed,
                        qnABoard.secret,
                        qnABoard.createdAt,
                        qnABoard.qnAAnswers.size(),
                        JPAExpressions
                                .selectFrom(qnABoard)
                                .where(qnABoard.writerId.eq(userId))
                                .exists()
                ))
                .from(qnABoard)
                .where(qnABoard.fixed.eq(true))
                .orderBy(qnABoard.createdAt.desc())
                .fetch();

        List<QnABoardResponse> list = queryFactory
                .select(new QQnABoardResponse(
                        qnABoard.id,
                        qnABoard.title,
                        qnABoard.writerId,
                        qnABoard.fixed,
                        qnABoard.secret,
                        qnABoard.createdAt,
                        qnABoard.qnAAnswers.size(),
                        JPAExpressions
                                .selectFrom(qnABoard)
                                .where(qnABoard.writerId.eq(userId))
                                .exists()
                ))
                .from(qnABoard)
                .where(qnABoard.fixed.eq(false), containSearch(searchWord))
                .orderBy(qnABoard.createdAt.desc())
                .fetch();

        fixedQna.addAll(list);
        return new PageImpl<>(fixedQna, pageable, fixedQna.size());
    }

    public Page<QnABoardResponse> getMyQnaBoard(String userId, Pageable pageable) {
        List<QnABoardResponse> list = queryFactory
                .select(new QQnABoardResponse(
                        qnABoard.id,
                        qnABoard.title,
                        qnABoard.writerId,
                        qnABoard.fixed,
                        qnABoard.secret,
                        qnABoard.createdAt,
                        qnABoard.qnAAnswers.size(),
                        JPAExpressions
                                .selectFrom(qnABoard)
                                .where(qnABoard.writerId.eq(userId))
                                .exists()
                ))
                .from(qnABoard)
                .where(qnABoard.writerId.eq(userId))
                .orderBy(qnABoard.createdAt.desc())
                .fetch();

        return new PageImpl<>(list, pageable, list.size());
    }

    private BooleanExpression containSearch(String searchWord) {
        return searchWord != null ? qnABoard.title.contains(searchWord) : null;
    }

    public QnAOneBoardResponse getOneQnaBoard(String userId, Long id) {
        // 문의글 정보
        QnAOneBoardResponse qnAOneBoardResponse = queryFactory
                .select(new QQnAOneBoardResponse(
                        qnABoard.id,
                        qnABoard.title,
                        qnABoard.content,
                        qnABoard.writerId,
                        qnABoard.fixed,
                        qnABoard.secret,
                        qnABoard.createdAt,
                        qnABoard.qnAAnswers.size(),
                        JPAExpressions
                                .selectFrom(qnABoard)
                                .where(qnABoard.writerId.eq(userId))
                                .exists()
                ))
                .from(qnABoard)
                .where(qnABoard.id.eq(id))
                .orderBy(qnABoard.createdAt.desc())
                .fetchFirst();

        // 댓글 목록
        List<QnAAnswerResponse> comments = queryFactory
                .select(new QQnAAnswerResponse(
                        qnAAnswer.id,
                        qnAAnswer.content,
                        qnAAnswer.adminId,
                        qnAAnswer.createdAt
                ))
                .where(qnAAnswer.qnABoard.id.eq(id))
                .from(qnAAnswer)
                .fetch();

        qnAOneBoardResponse.setComments(comments);
        return qnAOneBoardResponse;
    }

    @Transactional
    public void updateQnaBoard(Long id, HelpDeskRequest request) {
        queryFactory
                .update(qnABoard)
                .set(qnABoard.title, request.getTitle())
                .set(qnABoard.content, request.getContent())
                .set(qnABoard.fixed, request.isFixed())
                .set(qnABoard.secret, request.isSecret())
                .set(qnABoard.password, request.getPassword())
                .where(qnABoard.id.eq(id))
                .execute();
    }

    @Transactional
    public void updateQnaAnswer(Long id, HelpDeskRequest request) {
        queryFactory
                .update(qnAAnswer)
                .set(qnAAnswer.adminId, request.getWriterId())
                .set(qnAAnswer.content, request.getContent())
                .where(qnAAnswer.id.eq(id))
                .execute();
    }

}
