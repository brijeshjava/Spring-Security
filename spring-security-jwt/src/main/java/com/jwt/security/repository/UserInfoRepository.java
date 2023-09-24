package com.jwt.security.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.jwt.security.entity.UserInfo;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {
    Optional<UserInfo> findByName(String username);

}
