package com.samsung.sodam.db.repository;

import com.samsung.sodam.db.entity.TroubleCommentLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TroubleCommentLikeRepository extends JpaRepository<TroubleCommentLike, Long> {
}
