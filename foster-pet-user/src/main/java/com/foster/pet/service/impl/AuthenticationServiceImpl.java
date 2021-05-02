package com.foster.pet.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foster.pet.dto.authentication.AuthenticationFRPDTO;
import com.foster.pet.dto.authentication.AuthenticationRDTO;
import com.foster.pet.entity.Authentication;
import com.foster.pet.repository.AuthenticationRepository;
import com.foster.pet.service.AuthenticationService;
import com.foster.pet.service.prcr.AuthenticationProcessor;
import com.foster.pet.util.Encryptor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private AuthenticationProcessor authenticationProcessor;

	@Autowired
	private AuthenticationRepository authenticationRepository;

	@Override
	public AuthenticationFRPDTO findById(Long id) {
		log.info("Start - AuthenticationServiceImpl.findById - Id: {}", id);

		Authentication authentication = this.authenticationProcessor.exists(id);
		AuthenticationFRPDTO authenticationFRPDTO = this.mapper.map(authentication, AuthenticationFRPDTO.class);

		log.info("End - AuthenticationServiceImpl.findById - AuthenticationFRPDTO: {}", authenticationFRPDTO);
		return authenticationFRPDTO;
	}

	@Override
	public AuthenticationFRPDTO findByEmail(String email) {
		log.info("Start - AuthenticationServiceImpl.findByEmail - Email: {}", email);

		Authentication authentication = this.authenticationProcessor.exists(email);
		AuthenticationFRPDTO authenticationFRPDTO = this.mapper.map(authentication, AuthenticationFRPDTO.class);

		log.info("End - AuthenticationServiceImpl.findByEmail - AuthenticationFRPDTO: {}", authenticationFRPDTO);
		return authenticationFRPDTO;
	}

	@Override
	public Authentication register(Authentication authentication) {
		log.info("Start - AuthenticationServiceImpl.register - Authentication: {}", authentication);

		this.authenticationProcessor.alreadyExists(authentication.getEmail());

		String encodedPassword = Encryptor.encode(authentication.getPassword());
		authentication.setPassword(encodedPassword);
		authentication.setIsLocked(false);
		authentication.setIsEnabled(true);

		authentication = this.authenticationRepository.save(authentication);

		log.info("End - AuthenticationServiceImpl.register - Authentication: {}", authentication);
		return authentication;
	}

	@Override
	public AuthenticationFRPDTO edit(AuthenticationFRPDTO authenticationFRPDTO) {
		log.info("Start - AuthenticationServiceImpl.edit - AuthenticationFRPDTO: {}", authenticationFRPDTO);

		this.authenticationProcessor.exists(authenticationFRPDTO.getEmail());

		Authentication authentication = this.mapper.map(authenticationFRPDTO, Authentication.class);
		authentication = this.authenticationProcessor.merge(authentication);

		String encodedPassword = Encryptor.encode(authentication.getPassword());
		authentication.setPassword(encodedPassword);

		authentication = this.authenticationRepository.save(authentication);
		authenticationFRPDTO = this.mapper.map(authentication, AuthenticationFRPDTO.class);

		log.info("End - AuthenticationServiceImpl.edit - AuthenticationFRPDTO: {}", authenticationFRPDTO);
		return authenticationFRPDTO;
	}

	@Override
	public AuthenticationRDTO remove(Long id) {
		log.info("Start - AuthenticationServiceImpl.remove - Id: {}", id);

		Authentication authentication = this.authenticationProcessor.exists(id);
		this.authenticationRepository.deleteById(id);

		AuthenticationRDTO authenticationRDTO = this.mapper.map(authentication, AuthenticationRDTO.class);

		log.info("End - AuthenticationServiceImpl.remove - AuthenticationRDTO: {}", authenticationRDTO);
		return authenticationRDTO;
	}
}
