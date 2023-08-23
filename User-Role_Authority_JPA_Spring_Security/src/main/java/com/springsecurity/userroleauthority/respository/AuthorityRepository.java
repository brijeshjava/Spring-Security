package com.springsecurity.userroleauthority.respository;


import com.springsecurity.userroleauthority.security.Authority;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
}