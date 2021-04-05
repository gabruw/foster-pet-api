package com.foster.pet.exception.company;

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
@DisplayName("ExceptionHandler - CompanyNotFound")
public class CompanyNotFoundExceptionHandlerTest {

	@Autowired
	private CompanyNotFoundExceptionHandler companyNotFoundExceptionHandler;

	@Test
	@DisplayName("Handler to throw company not found")
	public void exceptionHandler() {
		CompanyNotFoundException exception = new CompanyNotFoundException();

		ResponseEntity<Object> response = this.companyNotFoundExceptionHandler.exceptionHandler(exception);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
}
