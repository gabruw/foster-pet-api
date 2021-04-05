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
@DisplayName("ExceptionHandler - TokenTypeInvalid")
public class TokenTypeInvalidExceptionHandlerTest {

	@Autowired
	private TokenTypeInvalidExceptionHandler tokenTypeInvalidExceptionHandler;

	@Test
	@DisplayName("Handler to throw token type is invalid")
	public void exceptionHandler() {
		TokenTypeInvalidException exception = new TokenTypeInvalidException();

		ResponseEntity<Object> response = this.tokenTypeInvalidExceptionHandler.exceptionHandler(exception);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}
}
