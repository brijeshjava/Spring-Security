package com.springsecurity.demotwo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {
    @GetMapping("/find")
    public String getMsg(){
        return "testing done !";
    }
    @GetMapping("api/test")
    public String apiTest(){
        return "Api Testing Done !";
    }
}
