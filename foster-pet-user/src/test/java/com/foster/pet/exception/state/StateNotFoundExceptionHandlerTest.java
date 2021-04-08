package com.foster.pet.exception.state;

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
@DisplayName("ExceptionHandler - StateNotFound")
public class StateNotFoundExceptionHandlerTest {

	@Autowired
	private StateNotFoundExceptionHandler stateNotFoundExceptionHandler;

	@Test
	@DisplayName("Handler to throw state not found")
	public void exceptionHandler() {
		StateNotFoundException exception = new StateNotFoundException();

		ResponseEntity<Object> response = this.stateNotFoundExceptionHandler.exceptionHandler(exception);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
}
