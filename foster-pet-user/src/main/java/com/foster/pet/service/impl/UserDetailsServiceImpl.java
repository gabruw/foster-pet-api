package com.foster.pet.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.foster.pet.entity.Authentication;
import com.foster.pet.security.entity.JwtUser;
import com.foster.pet.service.AuthenticationService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private AuthenticationService authenticationService;

	@Override
	public UserDetails loadUserByUsername(String email) {
		Authentication authentication = this.authenticationService.findByEmail(email);
		return this.mapper.map(authentication, JwtUser.class);
	}
}
