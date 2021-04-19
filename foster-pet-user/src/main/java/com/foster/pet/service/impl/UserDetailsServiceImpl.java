package com.foster.pet.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.foster.pet.dto.authentication.AuthenticationFRPDTO;
import com.foster.pet.security.entity.JwtUser;
import com.foster.pet.service.AuthenticationService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private AuthenticationService authenticationService;

	@Override
	public UserDetails loadUserByUsername(String email) {
		log.info("Start - UserDetailsServiceImpl.loadUserByUsername - Email: {}", email);

		AuthenticationFRPDTO authentication = this.authenticationService.findByEmail(email);
		JwtUser jwtUser = this.mapper.map(authentication, JwtUser.class);

		log.info("End - UserDetailsServiceImpl.loadUserByUsername - JwtUser: {}", jwtUser.toString());
		return jwtUser;
	}
}
