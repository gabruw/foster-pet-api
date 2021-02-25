package com.foster.pet.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.foster.pet.entity.Authentication;
import com.foster.pet.security.util.JwtUser;
import com.foster.pet.service.AuthenticationService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private AuthenticationService authenticationService;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<Authentication> authentication = this.authenticationService.findByEmail(email);
		if (authentication.isPresent()) {
			return JwtUser.authenticationTojwtUser(authentication.get());
		}

		throw new UsernameNotFoundException("Email não encontrado.");
	}
}
