package com.jwt.security.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.jwt.security.entity.RefreshToken;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Integer> {
    Optional<RefreshToken> findByToken(String token);
    Optional<RefreshToken> findByUserInfoId(int id);
}
