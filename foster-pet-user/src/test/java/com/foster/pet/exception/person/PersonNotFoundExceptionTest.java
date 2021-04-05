package com.foster.pet.exception.person;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.foster.pet.constant.ErrorCode;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Exception - PersonNotFound")
public class PersonNotFoundExceptionTest {

	@Test
	@DisplayName("Throw person not found")
	public void personNotFoundException() {
		PersonNotFoundException exception = new PersonNotFoundException();
		assertEquals(exception.getMessage(), ErrorCode.PERSON_NOT_FOUND.getMessage());
	}

	@Test
	@DisplayName("Throw person not found with message")
	public void personNotFoundExceptionWithMesage() {
		PersonNotFoundException exception = new PersonNotFoundException(ErrorCode.PERSON_NOT_FOUND.getMessage());
		assertEquals(exception.getMessage(), ErrorCode.PERSON_NOT_FOUND.getMessage());
	}

	@Test
	@DisplayName("Throw person not found with error code")
	public void personNotFoundExceptionWithErrorCode() {
		PersonNotFoundException exception = new PersonNotFoundException(ErrorCode.PERSON_NOT_FOUND);
		assertEquals(exception.getMessage(), ErrorCode.PERSON_NOT_FOUND.getMessage());
	}
}
