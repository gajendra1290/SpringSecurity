package com.konkanvalley.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.konkanvalley.model.JwtRequest;
import com.konkanvalley.model.JwtResponse;
import com.konkanvalley.service.UserService;
import com.konkanvalley.utility.JWTUtility;

@RestController
public class ResourceController {

	@Autowired
	private JWTUtility utility;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public String getSecuredData() {
		return "Hello fucking world";
	}
	
	@PostMapping("/authenticate")
	public JwtResponse authenticate(@RequestBody JwtRequest request) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Invalid credentials");
		}
		
		UserDetails user= userService.loadUserByUsername(request.getUsername());
		String token=utility.generateToken(user);
		
		return new JwtResponse(token);
	}
}
