package com.foster.pet.exception.authentication;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("ExceptionHandler - AuthenticationAlreadyExists")
public class AuthenticationAlreadyExistsExceptionHandlerTest {

	@Autowired
	private AuthenticationAlreadyExistsExceptionHandler authenticationAlreadyExistsExceptionHandler;

	@Test
	@DisplayName("Handler to throw authentication not found")
	public void exceptionHandler() {
		AuthenticationAlreadyExistsException exception = new AuthenticationAlreadyExistsException();

		ResponseEntity<Object> response = this.authenticationAlreadyExistsExceptionHandler.exceptionHandler(exception);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
}
