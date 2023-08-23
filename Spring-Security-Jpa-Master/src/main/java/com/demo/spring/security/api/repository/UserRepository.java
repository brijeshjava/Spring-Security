package com.demo.spring.security.api.repository;

import com.demo.spring.security.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>{

	User findByUsername(String username);

}
