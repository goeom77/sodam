package com.samsung.sodam.db.repository;

import com.samsung.sodam.api.request.ReviewRequest;
import com.samsung.sodam.db.entity.Counselor;
import com.samsung.sodam.db.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>{
}
