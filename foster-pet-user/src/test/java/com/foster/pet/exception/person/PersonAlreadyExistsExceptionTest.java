package com.foster.pet.exception.person;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.foster.pet.constant.ErrorCode;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Exception - PersonAlreadyExists")
public class PersonAlreadyExistsExceptionTest {

	@Test
	@DisplayName("Throw person already exists")
	public void personNotFoundException() {
		PersonAlreadyExistsException exception = new PersonAlreadyExistsException();
		assertEquals(exception.getMessage(), ErrorCode.PERSON_ALREADY_EXISTS.getMessage());
	}
}
