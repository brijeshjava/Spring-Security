package com.springsecurity.demoone.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FrontController {
    @GetMapping("/find")
    public String findMsg(){
        return "testing done!!";
    }
}
