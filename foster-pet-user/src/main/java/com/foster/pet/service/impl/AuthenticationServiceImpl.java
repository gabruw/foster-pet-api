package com.foster.pet.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foster.pet.dto.authentication.AuthenticationFRPDTO;
import com.foster.pet.dto.authentication.AuthenticationRDTO;
import com.foster.pet.dto.authentication.TokenRDTO;
import com.foster.pet.entity.Authentication;
import com.foster.pet.repository.AuthenticationRepository;
import com.foster.pet.service.AuthenticationService;
import com.foster.pet.service.prcr.AuthenticationProcessor;
import com.foster.pet.util.Encryptor;
import com.foster.pet.util.JwtUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private AuthenticationProcessor authenticationProcessor;

	@Autowired
	private AuthenticationRepository authenticationRepository;

	@Override
	public AuthenticationFRPDTO findById(Long id) {
		log.info("Start - AuthenticationServiceImpl.findById - Id: {}", id);

		Authentication authentication = this.authenticationProcessor.exists(id);
		AuthenticationFRPDTO authenticationFRPDTO = this.mapper.map(authentication, AuthenticationFRPDTO.class);

		log.info("End - AuthenticationServiceImpl.findById - AuthenticationFRPDTO: {}",
				authenticationFRPDTO.toString());
		return authenticationFRPDTO;
	}

	@Override
	public AuthenticationFRPDTO findByEmail(String email) {
		log.info("Start - AuthenticationServiceImpl.findByEmail - Email: {}", email);

		Authentication authentication = this.authenticationProcessor.exists(email);
		AuthenticationFRPDTO authenticationFRPDTO = this.mapper.map(authentication, AuthenticationFRPDTO.class);

		log.info("End - AuthenticationServiceImpl.findByEmail - AuthenticationFRPDTO: {}",
				authenticationFRPDTO.toString());
		return authenticationFRPDTO;
	}

	@Override
	public TokenRDTO refresh(String token) {
		log.info("Start - AuthenticationServiceImpl.refresh - Token: {}", token);

		token = token.substring(7);
		String refreshedToken = this.jwtTokenUtil.refresh(token);

		TokenRDTO returnedToken = new TokenRDTO(refreshedToken);

		log.info("End - AuthenticationServiceImpl.refresh - TokenRDTO: {}", returnedToken.toString());
		return returnedToken;
	}

	@Override
	public Authentication register(Authentication authentication) {
		log.info("Start - AuthenticationServiceImpl.persist - Authentication: {}", authentication.toString());

		this.authenticationProcessor.alreadyExists(authentication.getEmail());

		String encodedPassword = Encryptor.encode(authentication.getPassword());
		authentication.setPassword(encodedPassword);

		log.info("End - AuthenticationServiceImpl.persist - Authentication: {}", authentication.toString());
		return this.authenticationRepository.save(authentication);
	}

	@Override
	public AuthenticationRDTO remove(Long id) {
		log.info("Start - AuthenticationServiceImpl.remove - Id: {}", id);

		Authentication authentication = this.authenticationProcessor.exists(id);
		this.authenticationRepository.deleteById(id);

		AuthenticationRDTO authenticationRDTO = this.mapper.map(authentication, AuthenticationRDTO.class);

		log.info("End - AuthenticationServiceImpl.remove - AuthenticationRDTO: {}", authenticationRDTO.toString());
		return authenticationRDTO;
	}
}
