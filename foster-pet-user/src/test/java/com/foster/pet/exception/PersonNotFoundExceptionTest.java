package com.foster.pet.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.foster.pet.constant.ErrorCode;

@SpringBootTest
@ActiveProfiles("test")
public class PersonNotFoundExceptionTest {

	@Test
	public void personNotFoundException() {
		PersonNotFoundException exception = new PersonNotFoundException(ErrorCode.PERSON_NOT_FOUND.toString());
		assertEquals(exception.getMessage(), ErrorCode.PERSON_NOT_FOUND.toString());
	}
}
