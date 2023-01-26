package com.samsung.sodam.db.repository;

import com.samsung.sodam.db.entity.FavoriteCounselor;
import com.samsung.sodam.db.entity.FavoriteCounselorPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<FavoriteCounselor, FavoriteCounselorPK> {
}
