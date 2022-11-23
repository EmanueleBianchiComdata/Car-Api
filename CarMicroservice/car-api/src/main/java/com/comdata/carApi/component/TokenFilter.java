package com.comdata.carApi.component;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.comdata.carService.model.User;

@Component
public class TokenFilter extends OncePerRequestFilter{

	Token token;
	
	public TokenFilter(Token token) {
		this.token=token;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		if(!authorizedHeader(request)) {
			filterChain.doFilter(request, response);
			return;
		}
		String accessToken=getToken(request);
		if(!token.validateToken(accessToken)) {
			filterChain.doFilter(request, response);
			return;
		}
		
		authenticationContext(accessToken, request);
		filterChain.doFilter(request, response);
	}
	
	private void authenticationContext(String accessToken, HttpServletRequest request) {
		UserDetails userDetails= getUserDetails(accessToken);
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, null);
		authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}
	
	private UserDetails getUserDetails(String accessToken) {
		User user=new User();
		String[] arraySubject= token.getSubject(accessToken).split(",");
		UUID idUser= UUID.fromString(arraySubject[0]);
		user.setId(idUser);
		user.setEmail(arraySubject[1]);
		return user;
	}
	
	private boolean authorizedHeader(HttpServletRequest request) {
		String header = request.getHeader("Authorization");
        if (ObjectUtils.isEmpty(header) || !header.startsWith("Bearer")) {
            return false;
        }
 
        return true;
    }
 
	
	private String getToken(HttpServletRequest request) {
		String header= request.getHeader("Authorization");
		String tokenString= header.split(" ")[1].trim();
		return tokenString;
	}
	
}
