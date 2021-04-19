package com.foster.pet.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.foster.pet.constant.ErrorCode;
import com.foster.pet.dto.authentication.AuthenticationRDTO;
import com.foster.pet.entity.Authentication;
import com.foster.pet.exception.authentication.AuthenticationAlreadyExistsException;
import com.foster.pet.exception.authentication.AuthenticationNotFoundException;
import com.foster.pet.exception.token.TokenInvalidException;
import com.foster.pet.repository.AuthenticationRepository;
import com.foster.pet.service.AuthenticationService;
import com.foster.pet.util.Encryptor;
import com.foster.pet.util.JwtUtil;

import properties.authentication.AuthenticationInstance;
import properties.authentication.AuthenticationProperties;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("ServiceImpl - Authentication")
public class AuthenticationServiceImplTest extends AuthenticationProperties {

	@Autowired
	private JwtUtil jwtTokenUtil;

	@MockBean
	private AuthenticationRepository authenticationRepository;

	@Autowired
	private AuthenticationService authenticationService;

	@Mock
	private Authentication authentication;

	@BeforeEach
	public void init() {
		this.authentication = AuthenticationInstance.instace();
	}

	@Test
	@DisplayName("Find an authentication by Id")
	public void findById() {
		when(this.authenticationRepository.findById(ID)).thenReturn(Optional.of(this.authentication));

		Authentication returnedAuthentication = this.authenticationService.findById(ID);
		assertEquals(ID, returnedAuthentication.getId());
		assertEquals(ROLE, returnedAuthentication.getRole());
		assertEquals(EMAIL, returnedAuthentication.getEmail());
		assertEquals(PASSWORD, returnedAuthentication.getPassword());
	}

	@Test
	@DisplayName("Find an authentication by Id with invalid Id")
	public void findByIdWithAuthenticationNotFound() {
		AuthenticationNotFoundException exception = assertThrows(AuthenticationNotFoundException.class, () -> {
			Long invalidId = 2L;
			when(this.authenticationRepository.findById(ID)).thenReturn(Optional.of(this.authentication));

			this.authenticationService.findById(invalidId);
		});

		assertEquals(ErrorCode.AUTHENTICATION_NOT_FOUND.getMessage(), exception.getMessage());
	}

	@Test
	@DisplayName("Find an authentication by Email")
	public void findByEmail() {
		when(this.authenticationRepository.findByEmail(EMAIL)).thenReturn(Optional.of(this.authentication));

		Authentication returnedAuthentication = this.authenticationService.findByEmail(EMAIL);
		assertEquals(ID, returnedAuthentication.getId());
		assertEquals(ROLE, returnedAuthentication.getRole());
		assertEquals(EMAIL, returnedAuthentication.getEmail());
		assertEquals(PASSWORD, returnedAuthentication.getPassword());
	}

	@Test
	@DisplayName("Find an authentication by Email with invalid Email")
	public void findByEmailWithAuthenticationNotFound() {
		AuthenticationNotFoundException exception = assertThrows(AuthenticationNotFoundException.class, () -> {
			String invalidEmail = "invalid@invalid.com";
			when(this.authenticationRepository.findByEmail(EMAIL)).thenReturn(Optional.of(this.authentication));

			this.authenticationService.findByEmail(invalidEmail);
		});

		assertEquals(ErrorCode.AUTHENTICATION_NOT_FOUND.getMessage(), exception.getMessage());
	}

	@Test
	@DisplayName("Persist an authentication")
	public void persist() {
		when(this.authenticationRepository.save(any(Authentication.class))).thenReturn(this.authentication);

		Authentication returnedAuthentication = this.authenticationService.register(this.authentication);
		assertEquals(ID, returnedAuthentication.getId());
		assertEquals(ROLE, returnedAuthentication.getRole());
		assertEquals(EMAIL, returnedAuthentication.getEmail());
		assertTrue(Encryptor.match(PASSWORD, returnedAuthentication.getPassword()));
	}

	@Test
	@DisplayName("Persist an authentication with an email already existing")
	public void persistWithAuthenticationAlreadyExists() {
		AuthenticationAlreadyExistsException exception = assertThrows(AuthenticationAlreadyExistsException.class,
				() -> {
					when(this.authenticationRepository.findByEmail(EMAIL)).thenReturn(Optional.of(this.authentication));

					this.authenticationService.register(this.authentication);
				});

		assertEquals(ErrorCode.AUTHENTICATION_ALREADY_EXISTS.getMessage(), exception.getMessage());
	}

	@Test
	@DisplayName("Refresh token with a invalid token")
	public void refreshWithTokenInvalid() {
		TokenInvalidException exception = assertThrows(TokenInvalidException.class, () -> {
			when(this.jwtTokenUtil.isExpired(INVALID_TOKEN_TYPE)).thenReturn(false);

			this.authenticationService.refresh(INVALID_TOKEN);
		});

		assertEquals(ErrorCode.TOKEN_INVALID.getMessage(), exception.getMessage());
	}

	@Test
	@DisplayName("Delete an authentication by Id")
	public void deleteById() {
		doNothing().when(this.authenticationRepository).deleteById(ID);
		when(this.authenticationRepository.findById(ID)).thenReturn(Optional.of(this.authentication));

		AuthenticationRDTO returnedAuthentication = this.authenticationService.remove(ID);
		assertEquals(ROLE, returnedAuthentication.getRole());
		assertEquals(EMAIL, returnedAuthentication.getEmail());
		assertEquals(PASSWORD, returnedAuthentication.getPassword());

		verify(this.authenticationRepository, times(1)).deleteById(ID);
	}

	@Test
	@DisplayName("Delete an authentication by Id with invalid Id value")
	public void deleteByIdWithAuthenticationNotFound() {
		AuthenticationNotFoundException exception = assertThrows(AuthenticationNotFoundException.class, () -> {
			Long invalidId = 2L;
			when(this.authenticationRepository.findById(ID)).thenReturn(Optional.of(this.authentication));

			this.authenticationService.remove(invalidId);
		});

		assertEquals(ErrorCode.AUTHENTICATION_NOT_FOUND.getMessage(), exception.getMessage());
	}
}
