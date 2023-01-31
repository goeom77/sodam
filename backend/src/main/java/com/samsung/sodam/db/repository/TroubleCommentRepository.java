package com.samsung.sodam.db.repository;

import com.samsung.sodam.db.entity.TroubleComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TroubleCommentRepository extends JpaRepository<TroubleComment, Long> {

}
