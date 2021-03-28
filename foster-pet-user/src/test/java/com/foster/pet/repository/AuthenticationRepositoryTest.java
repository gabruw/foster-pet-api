package com.foster.pet.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.foster.pet.entity.Authentication;

import properties.authentication.AuthenticationInstance;
import properties.authentication.AuthenticationProperties;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Repository - Authentication")
public class AuthenticationRepositoryTest extends AuthenticationProperties {

	@MockBean
	private AuthenticationRepository authenticationRepository;

	@Mock
	private Authentication authentication;

	@BeforeEach
	public void init() {
		this.authentication = AuthenticationInstance.instace();
	}

	@Test
	@DisplayName("Find a authentication by Email")
	public void findByEmail() {
		when(this.authenticationRepository.findByEmail(EMAIL)).thenReturn(Optional.of(this.authentication));

		Optional<Authentication> returnedAuthentication = this.authenticationRepository.findByEmail(EMAIL);
		assertEquals(ID, returnedAuthentication.get().getId());
		assertEquals(ROLE, returnedAuthentication.get().getRole());
		assertEquals(EMAIL, returnedAuthentication.get().getEmail());
		assertEquals(PASSWORD, returnedAuthentication.get().getPassword());
	}
}
