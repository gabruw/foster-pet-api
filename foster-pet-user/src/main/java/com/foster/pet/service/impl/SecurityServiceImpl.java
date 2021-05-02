package com.foster.pet.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foster.pet.dto.token.TokenFRDTO;
import com.foster.pet.dto.token.TokenRDTO;
import com.foster.pet.dto.user.LoginDTO;
import com.foster.pet.entity.Authentication;
import com.foster.pet.repository.AuthenticationRepository;
import com.foster.pet.service.SecurityService;
import com.foster.pet.service.prcr.AuthenticationProcessor;
import com.foster.pet.service.prcr.SecurityProcessor;
import com.foster.pet.util.JwtUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SecurityServiceImpl implements SecurityService {

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private SecurityProcessor securityProcessor;

	@Autowired
	private AuthenticationProcessor authenticationProcessor;

	@Autowired
	private AuthenticationRepository authenticationRepository;

	@Override
	public TokenFRDTO login(LoginDTO loginDTO) {
		log.info("Start - SecurityServiceImpl.login - LoginDTO: {}", loginDTO);

		Authentication authentication = this.securityProcessor.check(loginDTO.getEmail());
		this.securityProcessor.matchPassword(loginDTO.getPassword(), authentication.getPassword());

		String name = this.authenticationProcessor.getName(loginDTO.getUserType(), authentication);
		String token = this.securityProcessor.authenticate(loginDTO.getEmail(), loginDTO.getPassword());

		TokenFRDTO tokenFRDTO = TokenFRDTO.builder().name(name).token(token).userType(loginDTO.getUserType())
				.role(authentication.getRole()).build();

		log.info("End - SecurityServiceImpl.login - TokenFRDTO: {}", tokenFRDTO);
		return tokenFRDTO;
	}

	@Override
	public TokenRDTO refresh(String token) {
		log.info("Start - SecurityServiceImpl.refresh - Token: {}", token);

		token = token.substring(7);
		String refreshedToken = this.jwtTokenUtil.refresh(token);

		TokenRDTO returnedToken = new TokenRDTO(refreshedToken);

		log.info("End - SecurityServiceImpl.refresh - TokenRDTO: {}", returnedToken);
		return returnedToken;
	}

	@Override
	public void lock(Long id) {
		log.info("Start - SecurityServiceImpl.lock - Id: {}", id);

		Authentication authentication = this.authenticationProcessor.exists(id);
		authentication.setIsLocked(true);

		this.authenticationRepository.save(authentication);

		log.info("End - SecurityServiceImpl.lock");
	}

	@Override
	public void unlock(Long id) {
		log.info("Start - SecurityServiceImpl.unlock - Id: {}", id);

		Authentication authentication = this.authenticationProcessor.exists(id);
		authentication.setIsLocked(false);

		this.authenticationRepository.save(authentication);

		log.info("End - SecurityServiceImpl.unlock");
	}

	@Override
	public void enable(Long id) {
		log.info("Start - SecurityServiceImpl.enable - Id: {}", id);

		Authentication authentication = this.authenticationProcessor.exists(id);
		authentication.setIsEnabled(true);

		this.authenticationRepository.save(authentication);

		log.info("End - SecurityServiceImpl.enable");
	}

	@Override
	public void unable(Long id) {
		log.info("Start - SecurityServiceImpl.unable - Id: {}", id);

		Authentication authentication = this.authenticationProcessor.exists(id);
		authentication.setIsEnabled(false);

		this.authenticationRepository.save(authentication);

		log.info("End - SecurityServiceImpl.unable");
	}
}
