package com.comdata.carService.auth;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

public class AuthRequest {
	
	@Email(message = "insert valid email (example@example.com)")
	private String email;
	
	@Size(min = 8, message = "min length password 8+ character")
	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
