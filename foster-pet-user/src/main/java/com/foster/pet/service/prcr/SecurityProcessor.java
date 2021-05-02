package com.foster.pet.service.prcr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.foster.pet.entity.Authentication;
import com.foster.pet.exception.authentication.AuthenticationNotFoundException;
import com.foster.pet.exception.authentication.LockedAccountException;
import com.foster.pet.exception.authentication.WrongPasswordException;
import com.foster.pet.util.Encryptor;
import com.foster.pet.util.JwtUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SecurityProcessor {

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private AuthenticationProcessor authenticationProcessor;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsService userDetailsService;

	public String authenticate(String email, String password) {
		log.info("Start - SecurityProcessor.authenticate - Email: {}, Password: -", email);

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email,
				password);

		org.springframework.security.core.Authentication authenticate = this.authenticationManager
				.authenticate(authenticationToken);
		SecurityContextHolder.getContext().setAuthentication(authenticate);

		UserDetails userDetails = this.userDetailsService.loadUserByUsername(email);
		String token = this.jwtTokenUtil.create(userDetails);

		log.info("End - SecurityProcessor.authenticate - Token: {}", token);
		return token;
	}

	public Authentication check(String email) {
		log.info("Start - AuthenticationProcessor.check - Email: {}", email);

		Authentication authentication = this.authenticationProcessor.exists(email);
		this.wasEnabled(authentication);
		this.wasLocked(authentication);

		log.info("End - AuthenticationProcessor.check - Authentication: {}", authentication);
		return authentication;
	}

	public void wasEnabled(Authentication authentication) {
		log.info("Start - AuthenticationProcessor.wasEnabled - Authentication: {}", authentication);

		if (!authentication.getIsEnabled()) {
			log.error("AuthenticationNotFoundException - Email: {}", authentication.getEmail());
			throw new AuthenticationNotFoundException();
		}

		log.info("End - AuthenticationProcessor.wasEnabled");
	}

	public void wasLocked(Authentication authentication) {
		log.info("Start - AuthenticationProcessor.wasLocked - Authentication: {}", authentication);

		if (authentication.getIsLocked()) {
			log.error("LockedAccountException - Email: {}", authentication.getEmail());
			throw new LockedAccountException();
		}

		log.info("End - AuthenticationProcessor.wasLocked");
	}

	public void matchPassword(String password, String encodedPassword) {
		log.info("Start - AuthenticationProcessor.matchPassword - Password: -, Encoded Password: -");

		Boolean isSamePassword = Encryptor.match(password, encodedPassword);
		if (!isSamePassword) {
			log.error("WrongPasswordException - Password: -");
			throw new WrongPasswordException();
		}

		log.info("End - AuthenticationProcessor.matchPassword");
	}
}
