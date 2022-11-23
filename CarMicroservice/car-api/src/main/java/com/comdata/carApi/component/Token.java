package com.comdata.carApi.component;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.comdata.carService.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class Token {

	private long EXPIRE_DURATION= 3600000;
	
	Logger log= LoggerFactory.getLogger(Token.class);
	
	@Value("codifica22112022comdata")
	private String key;
	
	public String generateToken(User user) {
		return Jwts.builder()
				.setSubject(user.getId()+","+user.getEmail())
				.setIssuer("ComData")
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis()+EXPIRE_DURATION))
				.signWith(SignatureAlgorithm.HS512, key)
				.compact();
	}
	
	public boolean validateToken(String token) {
		try {
		Jwts.parser().setSigningKey(key).parseClaimsJws(token);
		log.info("token: "+token);
		return true;
		} catch (ExpiredJwtException e) {
			log.error("token expired");
			return false;
		}
	}
	
	public String getSubject(String token) {
		return parseClaims(token).getSubject();
	}

	private Claims parseClaims(String token) {
		return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
	}
	
}
