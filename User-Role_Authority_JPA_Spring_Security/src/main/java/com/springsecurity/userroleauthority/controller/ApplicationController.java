package com.springsecurity.userroleauthority.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import static org.springframework.security.authorization.AuthorityAuthorizationManager.hasAuthority;

@RestController
@RequestMapping("/rest/auth")
public class ApplicationController {
    @PreAuthorize("hasAuthority('order.read')")
    @GetMapping("/msg")
    public String readMsg(){
        return "Welcome to here!!";
    }
    @GetMapping("/test")
    public String readTest(){
        return "Welcome to test!!";
    }
}
