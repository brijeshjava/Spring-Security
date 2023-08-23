package com.springsecurity.userroleauthority.respository;


import com.springsecurity.userroleauthority.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<Role, Integer> {
}