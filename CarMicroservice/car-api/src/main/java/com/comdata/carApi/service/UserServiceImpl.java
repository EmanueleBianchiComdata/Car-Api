package com.comdata.carApi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.comdata.carApi.component.Token;
import com.comdata.carService.auth.AuthRequest;
import com.comdata.carService.auth.AuthResponse;
import com.comdata.carService.model.User;
import com.comdata.carService.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	UserRepository repository;
	Token token;
	AuthenticationManager authManager;
	
	Logger log= LoggerFactory.getLogger(UserServiceImpl.class);
	
	public UserServiceImpl(UserRepository repository, AuthenticationManager authManager, Token token){
		this.repository=repository;
		this.authManager=authManager;
		this.token=token;
	}
	
	@Override
	public void saveUser(User user) {
		repository.save(user);
		log.info("save user "+user);
	}

	@Override
	public ResponseEntity<Object> accessApi(AuthRequest authRequest) {
		try {
			Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
			User user= (User) authentication.getPrincipal();
			String accessToken= token.generateToken(user);
			AuthResponse authResponse = new AuthResponse(user.getEmail(), accessToken);
			return ResponseEntity.ok(authResponse);
		} catch (BadCredentialsException e) {
			log.error("user does not exit");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}

}
