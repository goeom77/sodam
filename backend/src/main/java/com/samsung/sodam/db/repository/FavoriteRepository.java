package com.samsung.sodam.db.repository;

import com.samsung.sodam.db.entity.FavoriteCounselor;
import com.samsung.sodam.db.entity.FavoriteCounselorPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<FavoriteCounselor, FavoriteCounselorPK> {
    List<FavoriteCounselor> findAllByClientId(String clientId);
}
