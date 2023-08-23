package com.demo.spring.security.api.repository;

import com.demo.spring.security.api.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer>{

}
