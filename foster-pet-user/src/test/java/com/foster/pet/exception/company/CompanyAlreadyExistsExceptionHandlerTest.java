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
@DisplayName("ExceptionHandler - CompanyAlreadyExists")
public class CompanyAlreadyExistsExceptionHandlerTest {

	@Autowired
	private CompanyAlreadyExistsExceptionHandler companyAlreadyExistsExceptionHandler;

	@Test
	@DisplayName("Handler to throw company already exists")
	public void exceptionHandler() {
		CompanyAlreadyExistsException exception = new CompanyAlreadyExistsException();

		ResponseEntity<Object> response = this.companyAlreadyExistsExceptionHandler.exceptionHandler(exception);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
}
