package com.jwt.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.security.dto.AuthRequest;
import com.jwt.security.service.JwtService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

	private final AuthenticationManager authenticationManager;
   
    private final JwtService jwtService;
    
    @GetMapping("/test")
    public String getUser() {
    	return "user working";
    }
    
	   @PostMapping("/authenticate")
	    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
	        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
	         System.out.println("after authentication "+authentication);
	        if (authentication.isAuthenticated()) {
	        	System.out.println(" authenticated   ");
	            return jwtService.generateToken(authRequest.getUsername());
	        } else {
	            throw new UsernameNotFoundException("invalid user request !");
	        }


	   }

}
