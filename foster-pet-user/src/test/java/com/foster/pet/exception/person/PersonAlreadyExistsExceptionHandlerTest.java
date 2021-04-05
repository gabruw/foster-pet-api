package com.foster.pet.exception.person;

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
@DisplayName("ExceptionHandler - PersonAlreadyExists")
public class PersonAlreadyExistsExceptionHandlerTest {

	@Autowired
	private PersonAlreadyExistsExceptionHandler personAlreadyExistsExceptionHandler;

	@Test
	@DisplayName("Handler to throw person already exists")
	public void exceptionHandler() {
		PersonAlreadyExistsException exception = new PersonAlreadyExistsException();

		ResponseEntity<Object> response = this.personAlreadyExistsExceptionHandler.exceptionHandler(exception);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
}
