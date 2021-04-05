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
@DisplayName("ExceptionHandler - TokenEmpty")
public class TokenEmptyExceptionHandlerTest {

	@Autowired
	private TokenEmptyExceptionHandler tokenEmptyExceptionHandler;

	@Test
	@DisplayName("Handler to throw token empty is invalid")
	public void exceptionHandler() {
		TokenEmptyException exception = new TokenEmptyException();

		ResponseEntity<Object> response = this.tokenEmptyExceptionHandler.exceptionHandler(exception);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}
}
