package com.springsecurity.eventbased.security.listener;

import com.springsecurity.eventbased.respository.LoginFailureRepository;
import com.springsecurity.eventbased.respository.UserRepository;
import com.springsecurity.eventbased.security.LoginFailure;
import com.springsecurity.eventbased.security.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;


@Slf4j
@Component
@RequiredArgsConstructor
public class AuthenticationFailureListener {

    private final LoginFailureRepository loginFailureRepository;
    private final UserRepository userRepository;

    @EventListener
    public void listen(AuthenticationFailureBadCredentialsEvent event) {
        log.info("Login failure");

        if (event.getSource() instanceof UsernamePasswordAuthenticationToken) {
            UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) event.getSource();
            LoginFailure.LoginFailureBuilder builder = LoginFailure.builder();

            if (token.getPrincipal() instanceof String) {
                log.info("Attempted Username: " + token.getPrincipal());
                builder.username((String) token.getPrincipal());
                userRepository.findByUsername((String) token.getPrincipal()).ifPresent(builder::user);
            }

            if (token.getDetails() instanceof WebAuthenticationDetails) {
                WebAuthenticationDetails details = (WebAuthenticationDetails) token.getDetails();

                log.info("Source IP: " + details.getRemoteAddress());
                builder.sourceIp(details.getRemoteAddress());
            }
            LoginFailure failure = loginFailureRepository.save(builder.build());
            log.info("Failure Event: " + failure.getId());

            if (failure.getUser() != null) {
                lockUserAccount(failure.getUser());
            }
        }


    }

    private void lockUserAccount(User user) {
        List<LoginFailure> failures = loginFailureRepository.findAllByUserAndCreatedDateIsAfter(user,
                Timestamp.valueOf(LocalDateTime.now().minusDays(1)));

        if(failures.size() > 3){
            log.info("Locking User Account... ");
            user.setAccountNonLocked(false);
            userRepository.save(user);
        }
    }







}