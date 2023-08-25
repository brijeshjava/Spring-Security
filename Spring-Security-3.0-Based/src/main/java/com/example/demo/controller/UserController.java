package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.UserInfo;
import com.example.demo.service.UserInfoUserDetailsService;
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserInfoUserDetailsService service;
	
	  @PostMapping("/new")
	    public String addNewUser(@RequestBody UserInfo userInfo){
	        return service.addUser(userInfo);
	    }
}
