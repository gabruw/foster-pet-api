package com.foster.pet.service.prcr;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.foster.pet.entity.Authentication;
import com.foster.pet.exception.authentication.AuthenticationAlreadyExistsException;
import com.foster.pet.exception.authentication.AuthenticationNotFoundException;
import com.foster.pet.repository.AuthenticationRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AuthenticationProcessor {

	@Autowired
	private AuthenticationRepository authenticationRepository;

	public Authentication exists(Long id) {
		log.info("Start - AuthenticationProcessor.exists - Id {}", id);

		Optional<Authentication> optAuthentication = this.authenticationRepository.findById(id);
		if (optAuthentication.isEmpty()) {
			log.error("AuthenticationNotFoundException - Id: {}", id);
			throw new AuthenticationNotFoundException();
		}

		log.info("End - AuthenticationProcessor.exists - Authentication {}", optAuthentication.get());
		return optAuthentication.get();
	}

	public Authentication exists(String email) {
		log.info("Start - AuthenticationProcessor.exists - Email {}", email);

		Optional<Authentication> optAuthentication = this.authenticationRepository.findByEmail(email);
		if (optAuthentication.isEmpty()) {
			log.error("AuthenticationNotFoundException - Email: {}", email);
			throw new AuthenticationNotFoundException();
		}

		log.info("End - AuthenticationProcessor.exists - Authentication {}", optAuthentication.get());
		return optAuthentication.get();
	}

	public Authentication alreadyExists(String email) {
		log.info("Start - AuthenticationProcessor.alreadyExists - Name {}", email);

		Optional<Authentication> optAuthentication = this.authenticationRepository.findByEmail(email);
		if (optAuthentication.isPresent()) {
			log.error("AuthenticationAlreadyExistsException - Email: {}", email);
			throw new AuthenticationAlreadyExistsException();
		}

		log.info("End - AuthenticationProcessor.alreadyExists - Authentication {}", optAuthentication.get());
		return optAuthentication.get();
	}
}
