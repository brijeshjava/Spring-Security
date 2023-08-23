package com.springsecurity.eventbased.respository;



import com.springsecurity.eventbased.security.LoginFailure;
import com.springsecurity.eventbased.security.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;


public interface LoginFailureRepository extends JpaRepository<LoginFailure, Integer> {

    List<LoginFailure> findAllByUserAndCreatedDateIsAfter(User user, Timestamp timestamp);
}