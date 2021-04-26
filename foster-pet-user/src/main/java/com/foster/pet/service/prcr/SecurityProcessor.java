package com.foster.pet.service.prcr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.foster.pet.util.JwtUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SecurityProcessor {

	@Autowired
	private JwtUtil jwtTokenUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailsService;

	public String authenticate(String email, String password) {
		log.info("Start - SecurityProcessor.authenticate - Email: {}, Password: -", email);

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email,
				password);

		Authentication authenticate = this.authenticationManager.authenticate(authenticationToken);
		SecurityContextHolder.getContext().setAuthentication(authenticate);
		
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(email);
		String token = this.jwtTokenUtil.create(userDetails);

		log.info("End - SecurityProcessor.authenticate - Token: {}", token);
		return token;
	}
}
