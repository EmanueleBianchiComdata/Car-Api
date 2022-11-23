package com.comdata.carApi.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.comdata.carApi.service.UserServiceImpl;
import com.comdata.carService.auth.AuthRequest;
import com.comdata.carService.model.User;

@RestController
public class UserController {

	private UserServiceImpl service;
	private PasswordEncoder passwordEncoder;
	
	public UserController(UserServiceImpl service, PasswordEncoder passwordEncoder) {
		this.service=service;
		this.passwordEncoder=passwordEncoder;
	}
	
	@PostMapping("/login")
	public ResponseEntity<Object> login(@RequestBody @Valid AuthRequest authRequest) {
		return service.accessApi(authRequest);
	}
	
	@PostMapping("/register")
	public void register(@RequestBody @Valid User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		service.saveUser(user);
	}
	
}
