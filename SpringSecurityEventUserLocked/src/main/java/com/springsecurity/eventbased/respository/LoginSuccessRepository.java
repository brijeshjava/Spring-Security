package com.springsecurity.eventbased.respository;


import com.springsecurity.eventbased.security.LoginSuccess;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LoginSuccessRepository extends JpaRepository<LoginSuccess, Integer> {
}