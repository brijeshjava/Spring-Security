package com.springsecurity.eventbased.respository;


import com.springsecurity.eventbased.security.Authority;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
}