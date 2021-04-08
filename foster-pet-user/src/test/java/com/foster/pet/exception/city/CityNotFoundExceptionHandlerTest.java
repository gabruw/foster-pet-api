package com.foster.pet.exception.city;

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
@DisplayName("ExceptionHandler - CityNotFound")
public class CityNotFoundExceptionHandlerTest {

	@Autowired
	private CityNotFoundExceptionHandler cityNotFoundExceptionHandler;

	@Test
	@DisplayName("Handler to throw city not found")
	public void exceptionHandler() {
		CityNotFoundException exception = new CityNotFoundException();

		ResponseEntity<Object> response = this.cityNotFoundExceptionHandler.exceptionHandler(exception);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
}
