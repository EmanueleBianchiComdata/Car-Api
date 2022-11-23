package com.comdata.carApi.service;

import org.springframework.http.ResponseEntity;

import com.comdata.carService.auth.AuthRequest;
import com.comdata.carService.model.User;

public interface UserService {

	public void saveUser(User user);
	
	public ResponseEntity<Object> accessApi(AuthRequest authRequest);
	
}
