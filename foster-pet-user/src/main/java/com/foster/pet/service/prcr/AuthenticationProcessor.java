package com.foster.pet.service.prcr;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.foster.pet.constant.UserType;
import com.foster.pet.entity.Authentication;
import com.foster.pet.exception.authentication.AuthenticationAlreadyExistsException;
import com.foster.pet.exception.authentication.AuthenticationNotFoundException;
import com.foster.pet.exception.authentication.WrongPasswordException;
import com.foster.pet.exception.user.UserTypeNotRecognizedException;
import com.foster.pet.repository.AuthenticationRepository;
import com.foster.pet.util.Encryptor;

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

	public void alreadyExists(String email) {
		log.info("Start - AuthenticationProcessor.alreadyExists - Email {}", email);

		Optional<Authentication> optAuthentication = this.authenticationRepository.findByEmail(email);
		if (optAuthentication.isPresent()) {
			log.error("AuthenticationAlreadyExistsException - Email: {}", email);
			throw new AuthenticationAlreadyExistsException();
		}

		log.info("End - AuthenticationProcessor.alreadyExists");
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

	public String getName(UserType userType, Authentication authentication) {
		log.info("Start - AuthenticationProcessor.getName - UserType: {}, Authentication: {}", userType,
				authentication);

		String name;
		switch (userType) {
		case PERSON: {
			name = authentication.getPerson().getName();
			break;
		}
		case COMPANY: {
			name = authentication.getCompany().getTradeName();
			break;
		}
		case NGO:
		case EMPLOYEE: {
			name = "";
			break;
		}
		default:
			log.error("UserTypeNotRecognizedException - UserType: {}", userType);
			throw new UserTypeNotRecognizedException();
		}

		log.info("End - AuthenticationProcessor.getName - Name: {}", name);
		return name;
	}
}
