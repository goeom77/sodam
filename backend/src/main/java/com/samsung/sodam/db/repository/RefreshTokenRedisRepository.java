package com.samsung.sodam.db.repository;

import com.samsung.sodam.db.entity.RefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRedisRepository extends CrudRepository<RefreshToken, String> {
}