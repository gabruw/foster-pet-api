package com.foster.pet.exception.token;

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
@DisplayName("ExceptionHandler - TokenInvalid")
public class TokenInvalidExceptionHandlerTest {

	@Autowired
	private TokenInvalidExceptionHandler tokenInvalidExceptionHandler;

	@Test
	@DisplayName("Handler to throw token is invalid")
	public void exceptionHandler() {
		TokenInvalidException exception = new TokenInvalidException();

		ResponseEntity<Object> response = this.tokenInvalidExceptionHandler.exceptionHandler(exception);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}
}
