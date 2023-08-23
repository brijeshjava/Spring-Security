package com.springsecurity.eventbased.respository;


import com.springsecurity.eventbased.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<Role, Integer> {
}