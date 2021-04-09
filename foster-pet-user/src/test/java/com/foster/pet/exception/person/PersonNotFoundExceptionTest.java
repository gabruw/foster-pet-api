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
}
