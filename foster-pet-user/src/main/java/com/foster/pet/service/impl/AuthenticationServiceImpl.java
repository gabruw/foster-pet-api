package com.foster.pet.service.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foster.pet.dto.authentication.AuthenticationDTO;
import com.foster.pet.dto.authentication.AuthenticationRDTO;
import com.foster.pet.dto.token.TokenRDTO;
import com.foster.pet.entity.Authentication;
import com.foster.pet.exception.authentication.AuthenticationAlreadyExistsException;
import com.foster.pet.exception.authentication.AuthenticationNotFoundException;
import com.foster.pet.repository.AuthenticationRepository;
import com.foster.pet.service.AuthenticationService;
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
	private AuthenticationRepository authenticationRepository;

	@Override
	public Authentication findById(Long id) {
		log.info("Start - AuthenticationServiceImpl.findById - Id: {}", id);

		Optional<Authentication> optAuthentication = this.authenticationRepository.findById(id);
		if (optAuthentication.isEmpty()) {
			log.error("AuthenticationNotFoundException - Id: {}", id);
			throw new AuthenticationNotFoundException();
		}

		log.info("End - AuthenticationServiceImpl.findById - Authentication: {}", optAuthentication.get().toString());
		return optAuthentication.get();
	}

	@Override
	public Authentication findByEmail(String email) {
		log.info("Start - AuthenticationServiceImpl.findByEmail - Email: {}", email);

		Optional<Authentication> optAuthentication = this.authenticationRepository.findByEmail(email);
		if (optAuthentication.isEmpty()) {
			log.error("AuthenticationNotFoundException - Email: {}", email);
			throw new AuthenticationNotFoundException();
		}

		log.info("End - AuthenticationServiceImpl.findByEmail - Authentication: {}",
				optAuthentication.get().toString());
		return optAuthentication.get();
	}

	@Override
	public Authentication persist(AuthenticationDTO authenticationDTO) {
		log.info("Start - AuthenticationServiceImpl.persist - AuthenticationDTO: {}", authenticationDTO.toString());

		Authentication authentication = this.mapper.map(authenticationDTO, Authentication.class);

		Optional<Authentication> optAuthentication = this.authenticationRepository
				.findByEmail(authentication.getEmail());
		if (optAuthentication.isPresent()) {
			log.error("AuthenticationAlreadyExistsException - Email: {}", authentication.getEmail());
			throw new AuthenticationAlreadyExistsException();
		}

		String encodedPassword = Encryptor.encode(authentication.getPassword());
		authentication.setPassword(encodedPassword);

		log.info("End - AuthenticationServiceImpl.persist - Authentication: {}", authentication.toString());
		return this.authenticationRepository.save(authentication);
	}

	@Override
	public TokenRDTO refresh(String token) {
		log.info("Start - AuthenticationServiceImpl.refresh - Token: {}", token);

		token = token.substring(7);
		String refreshedToken = this.jwtTokenUtil.refresh(token);

		TokenRDTO returnedToken = new TokenRDTO();
		returnedToken.setToken(refreshedToken);

		log.info("End - AuthenticationServiceImpl.refresh - TokenRDTO: {}", returnedToken.toString());
		return returnedToken;
	}

	@Override
	public AuthenticationRDTO deleteById(Long id) {
		log.info("Start - AuthenticationServiceImpl.deleteById - Id: {}", id);

		Optional<Authentication> optAuthentication = this.authenticationRepository.findById(id);
		if (optAuthentication.isEmpty()) {
			log.error("AuthenticationNotFoundException - Id: {}", id);
			throw new AuthenticationNotFoundException();
		}

		this.authenticationRepository.deleteById(id);
		AuthenticationRDTO authenticationDTO = this.mapper.map(optAuthentication.get(), AuthenticationRDTO.class);

		log.info("End - AuthenticationServiceImpl.deleteById - AuthenticationDTO: {}", authenticationDTO.toString());
		return authenticationDTO;
	}
}
