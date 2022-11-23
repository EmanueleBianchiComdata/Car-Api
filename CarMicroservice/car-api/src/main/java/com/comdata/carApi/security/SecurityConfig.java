package com.comdata.carApi.security;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.comdata.carApi.component.TokenFilter;
import com.comdata.carService.repository.UserRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	UserRepository repository;
	TokenFilter filter;
	
	public SecurityConfig(UserRepository repository, TokenFilter filter) {
		this.repository=repository;
		this.filter=filter;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(username -> repository.findByEmail(username)
			.orElseThrow(() -> new UsernameNotFoundException("user "+username+" not found")));
	}
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.exceptionHandling().authenticationEntryPoint((request, response, ex) -> {
					response.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage());
		});
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.authorizeRequests().antMatchers("/login","/register", "/swagger-ui/index.html").permitAll()
								.anyRequest().authenticated();
		
		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
	}

}
