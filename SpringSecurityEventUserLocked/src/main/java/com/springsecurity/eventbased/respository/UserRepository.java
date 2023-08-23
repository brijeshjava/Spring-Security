package com.springsecurity.eventbased.respository;


import com.springsecurity.eventbased.security.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);


    List<User> findAllByAccountNonLockedAndLastModifiedDateIsBefore(Boolean locked, Timestamp timestamp);
}
