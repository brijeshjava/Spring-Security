package com.springsecurity.userroleauthority.respository;


import com.springsecurity.userroleauthority.security.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}
