package com.samsung.sodam.db.repository;

import com.samsung.sodam.db.entity.Counselor;
import com.samsung.sodam.db.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long>{

}
