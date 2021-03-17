package com.foster.pet.exception.handler;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.foster.pet.constant.ErrorCode;
import com.foster.pet.exception.PersonNotFoundException;

@SpringBootTest
@ActiveProfiles("test")
public class RestExceptionHandlerTest {

	@Autowired
	private RestExceptionHandler restExceptionHandler;

	@Test
	public void handlePersonNotFoundException() {
		PersonNotFoundException exception = new PersonNotFoundException(ErrorCode.PERSON_NOT_FOUND.toString());

		ResponseEntity<Object> response = restExceptionHandler.handlePersonNotFoundException(exception);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
}
